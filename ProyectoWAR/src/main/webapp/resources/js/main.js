function enter(event){
  if(event.keyCode == '13' || event.which == '13'){
    event.preventDefault();
    document.getElementById('form:search').click();
  }
}

function showExpModal(){
  document.getElementById('id01').style.display = 'block';
}

function hideModals(){
  // Expedientes
  document.getElementById('id01').style.display='none';
}