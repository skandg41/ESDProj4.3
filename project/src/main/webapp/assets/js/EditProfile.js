let update_profile = document.getElementById("user_profile_form");
let update_photo = document.getElementById("user_profile_image");
window.onload = fetchempdetails;



update_profile.addEventListener('submit', async (e) => {
    e.preventDefault();
    e.stopPropagation();
    if (update_profile.checkValidity() === true) {
        document.getElementById("update_profile").style.display = "none";
        document.getElementById("spinner-button").style.display = "block";

        let response = await fetch('api/employee/Profile', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json;charset=utf-8'
            },
            body: JSON.stringify({
                emp_id: sessionStorage.getItem('id'),
                email: document.getElementById('email').value,
                password: document.getElementById('password').value,
                first_name: document.getElementById('firstName').value,
                last_name: document.getElementById('lastName').value,
                title: document.getElementById('designation').value,
            })
        });

        try{
            let result = await response.json();
            document.getElementById("update_profile").style.display = "block";
            document.getElementById("spinner-button").style.display = "none";
            console.log(result);
            console.log("Update Successful");
            window.alert("Profile Updated Successfully click ok to continue");
            location.href = "EditProfile.html";
        }catch (err){
            console.log(result);
            document.getElementById("update_profile").style.display = "block";
            document.getElementById("spinner-button").style.display = "none";
        }
    }
});

async function fetchempdetails() {
    console.log("fetch_emp_details");
    console.log(sessionStorage.getItem(('id')));
    if (!sessionStorage.getItem('id')) {
        location.href = "index.html";
        return;
    }
    console.log("Sending Req");
    let response = await fetch('api/employee/get_details',{
        method: 'POST',
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
        body: JSON.stringify({
            emp_id: sessionStorage.getItem('id')
        })
    });
    console.log("Responce Received");
    let employee = await response.json(); // read response body and parse as JSON
    console.log(employee);
    document.getElementById("firstName").value = employee['first_name'];
    document.getElementById("lastName").value = employee['last_name'];
    document.getElementById("email").value = employee['email'];
    document.getElementById("password").value = employee['password'];
    document.getElementById("designation").value = employee['title'];

}
