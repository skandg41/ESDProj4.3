window.onload = checksession;

function logout(){
    sessionStorage.clear();
    window.alert("Logged out Successfully");
    location.href = "index.html";
}

function checksession(){
    if (!sessionStorage.getItem('id')) {
        location.href = "index.html";
        return;
    }
}