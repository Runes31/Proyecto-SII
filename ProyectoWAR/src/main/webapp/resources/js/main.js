function enter(event){
  if(event.keyCode == '13' || event.which == '13'){
    event.preventDefault();
    document.getElementById('form:search').click();
  }
}