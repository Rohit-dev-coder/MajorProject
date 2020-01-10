// default





function showContent(e)
{
   
    var id = e.getAttribute("id");
    let bodyTitle = document.querySelector("h1.h1");
    bodyTitle.innerHTML = id;

    let tabLinks = document.querySelector(".sidebar .nav-link.active");
    tabLinks.classList.remove("active");

    
    let atabLinks = document.querySelector(".sidebar #"+id);
    atabLinks.classList.add("active");    
}