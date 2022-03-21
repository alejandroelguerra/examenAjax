function Tarea(id,proyecto,usuario,titulo,prioridad,estado){
	this.id=0;
	this.proyecto=0;
	this.usuario=0;
	this.titulo="";
	this.prioridad="";
	this.estado="";	
}
function Tarea(id,proyecto,usuario,titulo,prioridad,estado){
	this.id=id;
	this.proyecto=proyecto;
	this.usuario=usuario;
	this.titulo=titulo;
	this.prioridad=prioridad;
	this.estado=estado;	
}

function crearTarea(){
var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	$(document).ajaxSend(function(e, xhr, options) {
		xhr.setRequestHeader(header, token);
	});
	let tarea = new Tarea();

	tarea.titulo=document.getElementsByName("inputNombre").value;
	tarea.prioridad=document.getElementsByName("inputPrioridad").value;
	tarea.usuario=document.getElementsByName("inputTrabajadores").value;
	
	$.ajax({
		url: '/tarea/crear',
		contentType: "application/json; charset=utf-8",
		data: JSON.stringify(tarea),
		dataType: "json",
		type: "POST",
		success: function(response) {
			let tarea;

			let res = response;

				tarea = res;
				console.log(res);
				nuevaTarea(tarea);

		},
		error: function() {

			console.log("Algo mal")
		}

	});
}
	function nuevaTarea(tarea){
	let tareas = document.getElementById("tarea");
	let tr = document.createElement('tr');
	var cel1 = document.createElement("td");
	var cel2 = document.createElement("td");
	var cel3 = document.createElement("td");
	cel1.textContent = tarea.titulo;
	cel2.textContent = oferta.prioridad;
	cel3.textContent = oferta.estado;
	tr.appendChild(cel1);
	tr.appendChild(cel2);
	tr.appendChild(cel3);

	var cel4 = document.createElement("td");
	var botoninf = document.createElement("button");
	botoninf.setAttribute("class","btn btn-success");
	botoninf.setAttribute("type","button");


	botoninf.textContent = "ver tarea";
	cel4.appendChild(botoninf);
	tr.appendChild(cel4);
	tareas.appendChild(tr);
}

