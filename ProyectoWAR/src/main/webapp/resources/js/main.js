function enter(event){
  if(event.keyCode == '13' || event.which == '13'){
    event.preventDefault();
    document.getElementById('form:search').click();
  }
}

function showExpModal(){
  document.getElementById('id01').style.display = 'block';
}

function showAluModal(){
  document.getElementById('id02').style.display = 'block';
}

function showMatriculaModal(){
  document.getElementById('id03').style.display = 'block';
}

function showPrefModal(){
  document.getElementById('id04').style.display = 'block';
}

function hideModals(){
  // Expedientes
  document.getElementById('id01').style.display='none';
  // Alumnos
  document.getElementById('id02').style.display='none';
  // Matriculas
  document.getElementById('id03').style.display='none';
  // Preferencias
  document.getElementById('id04').style.display='none';
}