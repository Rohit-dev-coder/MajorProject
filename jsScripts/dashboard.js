function showContent(e)
{
   
    let selectTab = e.text;
    let bodyTitle = document.querySelector("h1.h1");
    bodyTitle.innerHTML = selectTab;
    alert(e.classlist);

}