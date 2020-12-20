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
        let result = await response;
        console.log(`result${result}`);
        console.log(response);
        if(result["status"]===200){
            let data = response.json();
            document.getElementById("submit-button").style.display = "block";
            document.getElementById("spinner-button").style.display = "none";
            console.log(data);
            sessionStorage.setItem('id', data["emp_id"]);
            console.log(sessionStorage.getItem('id'));
            console.log("Type of");
            console.log((typeof(data["emp_id"])));
            console.log((typeof(sessionStorage.getItem('id'))));
            location.href = "Home.html";
        }else{
            document.getElementById("submit-button").style.display = "block";
            document.getElementById("spinner-button").style.display = "none";
            document.getElementById("login-alert").style.display = "block";
        }
    }
});