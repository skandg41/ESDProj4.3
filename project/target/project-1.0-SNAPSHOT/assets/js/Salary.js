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


let salary_form = document.getElementById('month-choice');

salary_form.addEventListener('submit', async (e) => {
    e.preventDefault();
    e.stopPropagation();
    if (salary_form.checkValidity() === true) {
        document.getElementById("fetch-salary").style.display = "none";
        document.getElementById("spinner-button").style.display = "block";

        let response = await fetch('api/salary/view_salary', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json;charset=utf-8'
            },
            body: JSON.stringify({
                employee: parseInt(sessionStorage.getItem('id')),
                payment_date: document.getElementById('month').value,
            })
        });
        try{
            console.log("Fetch req send");
            //console.log(response.json())
            let result = await response.json();
            console.log("Fetch request received");
            document.getElementById("fetch-salary").style.display = "block";
            document.getElementById("spinner-button").style.display = "none";
            console.log(result);

            document.getElementById("amount").value = result['amount'];
            document.getElementById("paydate").value = result['payment_date'];
            document.getElementById("description").value = result['description'];
        }catch (err){
            document.getElementById("fetch-salary").style.display = "block";
            document.getElementById("spinner-button").style.display = "none";
            document.getElementById("fetch-alert").style.display = "block";
        }
    }
});
