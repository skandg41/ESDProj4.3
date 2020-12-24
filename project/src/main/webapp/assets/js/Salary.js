//let getPDF = document.getElementById("salaryslip");
window.onload = fetchempdetails;

/*getPDF.addEventListener('submit', async (e) => {
    e.preventDefault();
    e.stopPropagation();
    if (update_profile.checkValidity() === true) {
        document.getElementById("update_profile").style.display = "none";
        document.getElementById("spinner-button").style.display = "block";

        let doc = new jsPDF();
        let elementHTML = $('#salarybody').html();
        let specialElementHandlers = {
            '#elementH': function (element, renderer) {
                return true;
            }
        };
        doc.fromHTML(elementHTML, 15, 15, {
            'width': 170,
            'elementHandlers': specialElementHandlers
        });

// Save the PDF
        doc.save('sample-document.pdf');

        try{
            let result = await response.json();
            document.getElementById("update_profile").style.display = "block";
            document.getElementById("spinner-button").style.display = "none";
            location.href = "EditProfile.html";
        }catch (err){
            console.log(result);
            document.getElementById("update_profile").style.display = "block";
            document.getElementById("spinner-button").style.display = "none";
        }
    }
});
*/
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

function generatePDF() {
    var doc = new jsPDF();


    doc.setFont("times");
    doc.setFontType("bold");
    doc.setFontSize(16);
    doc.text(30, 10, 'International Institue of Information Technology, Bangalore');


    doc.text(10,20,'---------------------------------------------------------------------------------------------------');
    doc.setFontSize(13);
    doc.setFontType('normal');
    doc.setFont("times")
    doc.text(20,40,'Employee Name ');
    doc.text(80,40,document.getElementById('firstName').value);
    doc.text(90,40,document.getElementById('lastName').value)
    doc.text(20,50,'Email_id ');
    doc.text(80,50,document.getElementById('email').value);
    doc.text(20,60,'Title ');
    doc.text(80,60,document.getElementById('title').value);
    doc.text(20,70,'Amount ');
    doc.text(80,70,document.getElementById('amount').value);
    doc.text(20,80,'Salary Month ');
    doc.text(80,80,document.getElementById('paydate').value);
    doc.text(20,90,'Description ');
    doc.text(80,90,document.getElementById('description').value);
    doc.text(20,100,'---------------------------------------------------------------------------------------------------');
    doc.text(20,110,'This is an autogenerated PDF therefore does not require any signature');

    doc.save('iitb_emp_salary.pdf'); // Save the PDF with name "katara"...
}


