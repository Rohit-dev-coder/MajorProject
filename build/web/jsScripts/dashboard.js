// default

window.onresize = function() {
       this.onloadmyfunction();
};
window.onload = function() {
    this.onloadmyfunction();
};

function onloadmyfunction()
{
    var x = window.innerWidth;
    let Links = document.querySelector(".nav");
    if (x<768)
    {        
        let navbar = document.querySelector(".navbar-nav");
        navbar.append(Links);
    }
    else{
        let side = document.querySelector(".sidebar-sticky");
        side.append(Links);
    }
}



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