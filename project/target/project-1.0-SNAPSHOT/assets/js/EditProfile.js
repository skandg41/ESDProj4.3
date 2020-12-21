let update_profile = document.getElementById("user_profile_form");

window.onload = fetchempdetails;

// Example starter JavaScript for disabling form submissions if there are invalid fields
(function () {
    'use strict'

    // Fetch all the forms we want to apply custom Bootstrap validation styles to
    var forms = document.querySelectorAll('.needs-validation')

    // Loop over them and prevent submission
    Array.prototype.slice.call(forms)
        .forEach(function (form) {
            form.addEventListener('submit', function (event) {
                if (!form.checkValidity()) {
                    event.preventDefault()
                    event.stopPropagation()
                }

                form.classList.add('was-validated')
            }, false)
        })
})()

async function fetchempdetails() {
    console.log("fetch_emp_details");
    console.log(sessionStorage.getItem(('id')));
    if (!sessionStorage.getItem('id')) {
        location.href = "index.html";
        return;
    }
    let request = sessionStorage.getItem('id');

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
    document.getElementById("designation").value = employee['title'];

}
