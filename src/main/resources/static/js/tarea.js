function Tarea(){
	this.id_tarea=0;
	this.id_proyecto=0;
	this.id_usuario=0;
	this.titulo="";
	this.descripcion="";
	this.prioridad="";
	this.estado="";
}

function Tarea(id_tarea,id_proyecto,id_usuario,titulo,descripcion,prioridad,estado){
	this.id_tarea=id_tarea;
	this.id_proyecto=id_proyecto;
	this.id_usuario=id_usuario;
	this.titulo=titulo;
	this.descripcion=descripcion;
	this.prioridad=prioridad;
	this.estado=estado;
}
function Tarea(id_proyecto,id_usuario,titulo,descripcion,prioridad,estado){
	this.id_proyecto=id_proyecto;
	this.id_usuario=id_usuario;
	this.titulo=titulo;
	this.descripcion=descripcion;
	this.prioridad=prioridad;

}

function Tarea(id_proyecto,id_usuario,titulo,descripcion,prioridad){
	this.id_proyecto=id_proyecto;
	this.id_usuario=id_usuario;
	this.titulo=titulo;
	this.descripcion=descripcion;
	this.prioridad=prioridad;

}
function Tarea(titulo,descripcion,prioridad,estado){
	this.titulo = titulo;
	this.descripcion = descripcion;
	this.prioridad = prioridad;
	this.estado = estado;
}

