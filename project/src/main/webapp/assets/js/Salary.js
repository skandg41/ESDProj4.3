window.onload = fetchempdetails;

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
    document.getElementById("title").value = employee['title'];
}

