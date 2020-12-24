let update_profile = document.getElementById("user_profile_form");
let imageUpload = document.getElementById("user_profile_image");

window.onload = fetchempdetails;
//window.onload = retriveImage;

imageUpload.addEventListener('submit', async (e) => {
    e.preventDefault();
    e.stopPropagation();
    if (imageUpload.checkValidity() === true) {
        document.getElementById("upload").style.display = "none";
        document.getElementById("spinner-button-upload").style.display = "block";

        let form_data = new FormData();
        form_data.append('file', document.getElementById('formFile').files[0]);
        form_data.append('emp_id', sessionStorage.getItem('id'));
        console.log(form_data);
        let response = await fetch('api/employee/uploadImage', {
            method: 'POST',
            body: form_data
        });
        try {
            let res = response.json();
            document.getElementById("upload").style.display = "block";
            document.getElementById("spinner-button-upload").style.display = "none";

            console.log("Update Successful");
            window.alert("Profile Updated Successfully click ok to continue");
            location.href = "EditProfile.html";
        } catch (err) {
            console.log(err);
            document.getElementById("upload").style.display = "block";
            document.getElementById("spinner-button-upload").style.display = "none";
        }
    }
});

/*update_photo.addEventListener('submit',async (e) => {
    e.preventDefault();
    e.stopPropagation();
    //final Client client = ClientBuilder.newBuilder().register(MultiPartFeature.class).build();
    if (update_profile.checkValidity() === true) {
        document.getElementById("update_profile").style.display = "none";
        document.getElementById("spinner-button").style.display = "block";

        let response = await fetch('api/employee/uploadImage', {


            method: 'POST',
            headers: {
                'Content-Type': ''
                multipart/form-data
            },
            body: JSON
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
});*/
/*update_photo.addEventListener('submit',async (e) => {
        /*TODO: Add SDKs for Firebase products that you want to use
        https://firebase.google.com/docs/web/setup#available-libraries -->

    e.preventDefault();
    e.stopPropagation();
        // Your web app's Firebase configuration
        let firebaseConfig = {
        apiKey: "AIzaSyBE3zTRflM5gtpVGpfWwBJ_id9D-x9t5Mk",
        authDomain: "a-erp-8c5e1.firebaseapp.com",
        projectId: "a-erp-8c5e1",
        storageBucket: "a-erp-8c5e1.appspot.com",
        messagingSenderId: "343669538376",
        appId: "1:343669538376:web:c73c360f68d7ae4e83227d"
    };
        // Initialize Firebase
    firebase.initializeApp(firebaseConfig);
    console.log("Initialized");
    // Get a reference to the storage service, which is used to create references in your storage bucket
    let storage = firebase.storage();
    // Create a storage reference from our storage service
    let storageRef = storage.ref();
    // Create a child reference
    let userImages = storageRef.child('UserImages');
    console.log("Configured");
    // File or Blob named mountains.jpg
    let file = document.getElementById("formFile");

    // Create the file metadata
    let metadata = {
        contentType: 'image/jpeg'
    };

    // Upload file and metadata to the object 'images/mountains.jpg'
    let uploadTask = userImages.child(file.name).put(file, metadata);
    console.log("Uploaded");
    // Listen for state changes, errors, and completion of the upload.
    uploadTask.on(firebase.storage.TaskEvent.STATE_CHANGED, // or 'state_changed'
        function(snapshot) {
            // Get task progress, including the number of bytes uploaded and the total number of bytes to be uploaded
            let progress = (snapshot.bytesTransferred / snapshot.totalBytes) * 100;
            console.log('Upload is ' + progress + '% done');
            switch (snapshot.state) {
                case firebase.storage.TaskState.PAUSED: // or 'paused'
                    console.log('Upload is paused');
                    break;
                case firebase.storage.TaskState.RUNNING: // or 'running'
                    console.log('Upload is running');
                    break;
            }
        }, function(error) {

            // A full list of error codes is available at
            // https://firebase.google.com/docs/storage/web/handle-errors
        }, function() {
            // Upload completed successfully, now we can get the download URL
            uploadTask.snapshot.ref.getDownloadURL().then(function(downloadURL) {
                console.log('File available at', downloadURL);
            });
        });
})*/


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

    console.log("Response Received");
    let employee = await response.json(); // read response body and parse as JSON
    console.log("Response");
    console.log(employee['first_name']);
    document.getElementById("firstName").value = employee['first_name'];
    document.getElementById("lastName").value = employee['last_name'];
    document.getElementById("email").value = employee['email'];
    //document.getElementById("user_img").value = employee['photograph_path'];
    document.getElementById("password").value = employee['password'];
    document.getElementById("designation").value = employee['title'];

    console.log(employee['photograph_path']);
    if(employee['photograph_path'] !== null || employee['photograph_path'] !== "null" )
    {
        let path = "api/employee/images/" + employee['photograph_path'];
        document.getElementById("user_img").src = path;
        console.log("src path set");
    }

}

/*async function retriveImage(){
    if (!sessionStorage.getItem('id')) {
        location.href = "index.html";
        return;
    }
    console.log("Sending Req");
    let response = await fetch('api/employee/retriveImage',{
        method: 'POST',
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
        body: JSON.stringify({
            emp_id: sessionStorage.getItem('id')
        })
    });

    console.log(response);
}*/