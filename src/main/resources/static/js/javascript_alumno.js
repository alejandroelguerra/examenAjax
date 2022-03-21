document.addEventListener("DOMContentLoaded", pintarTareas);

document.addEventListener("DOMContentLoaded", function() {
	$("#flexSwitchCheckChecked").click(misTareas);
})

const arrayTareas = new Array();


function tareaDom(tarea) {
	let divCard = document.createElement("div");
	divCard.classList.add("card", "draggable", "shadow-sm");
	divCard.setAttribute("id", tarea.id);
	divCard.setAttribute("draggable", "true");
	divCard.setAttribute("ondragstart", "drag(event)");

	let divCardBody = document.createElement("div");
	divCardBody.classList.add("card-body", "p-2");



	let divCardTitle = document.createElement("div");
	divCardTitle.classList.add("card-title");

	let span = document.createElement("span");

	switch (tarea.prioridad) {
		case "BAJA":
			span.classList.add("badge", "rounded-pill", "bg-success");
			span.setAttribute("id", "span");
			span.textContent = tarea.prioridad;
			break;
		case "MEDIA":
			span.classList.add("badge", "rounded-pill", "bg-Warning");
			span.setAttribute("id", "span");
			span.textContent = tarea.prioridad;
			break;

		case "ALTA":
			span.classList.add("badge", "rounded-pill", "bg-danger");
			span.setAttribute("id", "span");
			span.textContent = tarea.prioridad;
			break;
	}


	let link = document.createElement("a");
	link.classList.add("lead", "font-weight-light");
	link.textContent = tarea.id;



	let para = document.createElement("p");
	para.setAttribute("id", "cartaTitulo");
	para.textContent = tarea.titulo;



	let button = document.createElement("button");
	button.classList.add("btn", "btn-primary", "btn-sm");
	button.textContent = "View";
	//button.setAttribute('onClick', "$('#editar_modal').show()");
	button.addEventListener("click", function() {
		$(editar_modal).show();
		rellenarModal(tarea);
	});
	divCardTitle.appendChild(span);
	divCardTitle.appendChild(link);

	divCardBody.appendChild(divCardTitle);
	divCardBody.appendChild(para);
	divCardBody.appendChild(button);

	divCard.appendChild(divCardBody);

	let divDropZone = document.createElement("div");
	divDropZone.classList.add("dropzone", "rounded");
	divCard.setAttribute("ondrop", "drop(event)");
	divCard.setAttribute("ondragover", "allowDrop(event)");
	divCard.setAttribute("ondragleave", "clearDrop(event)");
	let columna;
	switch (tarea.estado) {
		case "Preparada":
			columna = document.getElementsByClassName("columna")[0]
			columna.appendChild(divCard);
			columna.appendChild(divDropZone);
			break;
		case "En Curso":
			columna = document.getElementsByClassName("columna")[1]
			columna.appendChild(divCard);
			columna.appendChild(divDropZone);
			break;

		case "En revision":
			columna = document.getElementsByClassName("columna")[2]
			columna.appendChild(divCard);
			columna.appendChild(divDropZone);
			break;
		case "Finalizada":
			columna = document.getElementsByClassName("columna")[3]
			columna.appendChild(divCard);
			columna.appendChild(divDropZone);
			break;
	}

}
function verTareas(response) {

	for (let tarea of response) {
		tareaDom(tarea);
		arrayTareas.push(tarea);
	}
}

function pintarTareas() {

	var token = $("meta[name='_csrf']").attr("content");
	var id = document.getElementById("idProyecto").value;
	fetch('/todos/proyecto/' + id, {
		headers: {
			"Content-Type": "application/json; charset=utf-8",
			"X_CSRF-TOKEN": token
		}
	})
		.then(res => res.json())
		.then(response => {
			verTareas(response);
			console.log(arrayTareas);
		})
}



function crearTarea() {

	var token = $("meta[name='_csrf']").attr("content");

	var idProyecto = document.getElementById("idProyecto").value;
	var idUsuario = document.getElementById("idUsuario").value;
	var nombre = document.getElementById("inputNombreCrear").value;
	var desc = document.getElementById("inputDescripcionCrear").value;
	var prioridad = document.getElementById("inputPrioridadCrear").value;

	let tarea = new Tarea(nombre, desc, prioridad, 'Preparada');
	fetch('/anadir/' + idProyecto, {
		headers: {
			credentials: 'same-origin',
			'Content-type': 'application/json',
			'X-CSRF-TOKEN': token
		},
		method: 'POST',
		body: JSON.stringify(tarea)
	})
		.then(function(response) {
			if (response.ok) {
				return response.json();

			} else {
				throw "No va";

			}
		}).then(res => {
			tarea = res;
			tareaDom(tarea);
			arrayTareas.push(tarea);
			$('#crear_modal').hide();
			console.log(res);
		});

}
function rellenarModal(tarea) {

	var x = arrayTareas.find(function(array, index) {
		if (array.id == tarea.id)
			return true;
	});

	var id = document.getElementById("inputId").value = x.id;
	document.getElementById("inputNombre").value = x.titulo;
	document.getElementById("inputDescripcion").value = x.descripcion;
	var prioridad = document.getElementById("inputPrioridad").value = x.prioridad;
	document.getElementById("inputEmpleado").value = x.usuario;
	var option1 = document.getElementById("opcion1");
	var option2 = document.getElementById("opcion2");
	var option3 = document.getElementById("opcion3");
	if (prioridad == 'BAJA') {
		option1.setAttribute('selected', 'selected');
	} else if (prioridad == 'MEDIA') {
		option2.setAttribute('selected', 'selected');
	} else {
		option3.setAttribute('selected', 'selected');
	}
}


function editarTarea() {
	var idTarea = document.getElementById("inputId").value;
	var nombre = document.getElementById("inputNombre").value;
	var desc = document.getElementById("inputDescripcion").value;
	var prioridad = document.getElementById("inputPrioridad").value;
	var usuario = document.getElementById("inputTrabajadores").value;

	var token = $("meta[name='_csrf']").attr("content");
	fetch("/modificar/" + idTarea, {
		headers: { "Content-Type": "application/json; charset=utf-8", 'X-CSRF-TOKEN': token }, method: 'POST',
		credentials: 'same-origin',
		body: JSON.stringify({
			titulo: nombre, descripcion: desc, prioridad: prioridad, estado: "Preparada", empleado: usuario
		})
	})
		.then(function(response) {
			if (response.ok) {
				return response.json();
			}
		}).then(tarea => {
			vaciarColumnas();
			arrayTareas.length = 0;
			pintarTareas();
			$('#editar_modal').hide();

		})
}

function vaciarColumnas() {
	let columna0 = document.getElementsByClassName("columna")[0];
	let columna1 = document.getElementsByClassName("columna")[1];
	let columna2 = document.getElementsByClassName("columna")[2];
	let columna3 = document.getElementsByClassName("columna")[3];
	columna0.replaceChildren();
	columna1.replaceChildren();
	columna2.replaceChildren();
	columna3.replaceChildren();
}

function eliminarTarea() {


	var id = document.getElementById("inputId").value;

	fetch("/borrar/" + id, { headers: { "Content-Type": "application/json; charset=utf-8" } })
		.then(data => console.log(data)
		).then(function() {
			var id = document.getElementById("inputId").value;
			var carta = document.getElementById(id);
			carta.parentNode.removeChild(carta);
			var idarray = arrayTareas.find(function(array, index) {
				if (array.id == id)
					return true;
			});
			var posArray = arrayTareas.findIndex(x => x.id === idarray.id);
			arrayTareas.splice(parseInt(posArray), 1);
			console.log(arrayTareas);
			$('#editar_modal').hide();
		})


}

function misTareas() {
	var token = $("meta[name='_csrf']").attr("content");

	let check = document.querySelector('#flexSwitchCheckChecked');

	fetch('/misTareas', {
		headers: {
			credentials: 'same-origin',
			'Content-type': 'application/json',
			'X-CSRF-TOKEN': token
			
		}
	})
		.then(res => res.json())
		.then(tareas => {
			if (check.checked == true) {
				vaciarColumnas();

				for (let tarea of tareas) {

					tareaDom(tarea);
				}
			} else {
				arrayTareas.length = 0;
				vaciarColumnas();
				pintarTareas();
			}
		})
}




