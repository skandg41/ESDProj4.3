let login_form = document.getElementById('login-validation');

login_form.addEventListener('submit', async (e) => {
    e.preventDefault();
    e.stopPropagation();
    if (login_form.checkValidity() === true) {
        document.getElementById("submit-button").style.display = "none";
        document.getElementById("spinner-button").style.display = "block";

        let response = await fetch('api/employee/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json;charset=utf-8'
            },
            body: JSON.stringify({
                email: document.getElementById('email').value,
                password: document.getElementById('password').value,
            })
        });

        try{
            let result = await response.json();
            document.getElementById("submit-button").style.display = "block";
            document.getElementById("spinner-button").style.display = "none";
            console.log(result);
            sessionStorage.setItem('id', result["emp_id"]);
            console.log(sessionStorage.getItem('id'));
            location.href = "Home.html";
        }catch (err){
            document.getElementById("submit-button").style.display = "block";
            document.getElementById("spinner-button").style.display = "none";
            document.getElementById("login-alert").style.display = "block";

        }
    }
});