import java.util.*

class Hobby(val titulo: String, val descripcion: String, val urlPhoto: String? = null)

class PerfilUsuario(
    val id: Int,
    val nombres: String,
    val apellidos: String,
    val urlPhoto: String? = null,
    val edad: Int,
    val correo: String,
    val biografia: String? = null,
    var estado: String,
    val hobbies: MutableList<Hobby> = mutableListOf()
) {
    fun agregarHobby(hobby: Hobby) {
        hobbies.add(hobby)
    }
}

fun main() {
    val listaPerfiles: MutableList<PerfilUsuario> = mutableListOf(
        PerfilUsuario(1, "Juan", "Perez", "url1", 30, "juan@example.com", "Biografía 1", "Activo", mutableListOf(Hobby("Hobby1", "Descripción hobby 1", "urlHobby1"))),
        PerfilUsuario(2, "María", "Lopez", "url2", 25, "maria@example.com", "Biografía 2", "Pendiente", mutableListOf(Hobby("Hobby2", "Descripción hobby 2", "urlHobby2")))
    )

    var opcion: Int
    do {
        println("----- Menú -----")
        println("1. Crear perfil")
        println("2. Buscar perfil de usuario(s)")
        println("3. Eliminar perfil")
        println("4. Agregar Hobby")
        println("5. Salir")
        print("Ingrese la opción: ")
        opcion = readLine()?.toIntOrNull() ?: 0

        when (opcion) {
            1 -> {
                try {
                    val id = listaPerfiles.size + 1
                    print("Ingrese los nombres: ")
                    val nombres = readLine()!!
                    print("Ingrese los apellidos: ")
                    val apellidos = readLine()!!
                    print("Ingrese la URL de la foto de perfil (puede ser nulo): ")
                    val urlPhoto = readLine()
                    print("Ingrese la edad: ")
                    val edad = readLine()?.toIntOrNull() ?: 0
                    print("Ingrese el correo electrónico: ")
                    val correo = readLine()!!
                    print("Ingrese la biografía (puede ser nulo): ")
                    val biografia = readLine()
                    print("Ingrese el estado (Activo, Pendiente, Inactivo): ")
                    val estado = readLine()?.capitalize() ?: "Pendiente"

                    val nuevoPerfil = PerfilUsuario(id, nombres, apellidos, urlPhoto, edad, correo, biografia, estado)
                    listaPerfiles.add(nuevoPerfil)
                    println("Perfil creado exitosamente.")
                } catch (e: Exception) {
                    println("Error al crear el perfil. Intente nuevamente.")
                }
            }
            2 -> {
                print("Ingrese el ID o el nombre y/o apellidos del perfil a buscar: ")
                val searchTerm = readLine()!!.trim()

                val results = listaPerfiles.filter { it.id.toString() == searchTerm || it.nombres.contains(searchTerm, true) || it.apellidos.contains(searchTerm, true) }
                if (results.isNotEmpty()) {
                    for (perfil in results) {
                        println("----- Perfil encontrado -----")
                        println("ID: ${perfil.id}")
                        println("Nombres: ${perfil.nombres}")
                        println("Apellidos: ${perfil.apellidos}")
                        println("URL de foto de perfil: ${perfil.urlPhoto ?: "N/A"}")
                        println("Edad: ${perfil.edad}")
                        println("Correo: ${perfil.correo}")
                        println("Biografía: ${perfil.biografia ?: "N/A"}")
                        println("Estado: ${perfil.estado}")

                        if (perfil.hobbies.isNotEmpty()) {
                            println("Hobbies:")
                            for (hobby in perfil.hobbies) {
                                println("  - Título: ${hobby.titulo}")
                                println("    Descripción: ${hobby.descripcion}")
                                println("    URL de foto del hobby: ${hobby.urlPhoto ?: "N/A"}")
                            }
                        } else {
                            println("Hobbies: No tiene hobbies.")
                        }
                    }
                } else {
                    println("No se encontraron perfiles con la información ingresada.")
                }
            }
            3 -> {
                print("Ingrese el ID del perfil a eliminar: ")
                val idToDelete = readLine()?.toIntOrNull()

                if (idToDelete != null) {
                    val removed = listaPerfiles.removeIf { it.id == idToDelete }
                    if (removed) {
                        println("Perfil con ID $idToDelete eliminado exitosamente.")
                    } else {
                        println("No se encontró un perfil con el ID $idToDelete.")
                    }
                } else {
                    println("ID inválido. Intente nuevamente.")
                }
            }
            4 -> {
                print("Ingrese el ID o el nombre y/o apellidos del perfil al que desea agregar un hobby: ")
                val searchTerm = readLine()!!.trim()

                val profileToAddHobby = listaPerfiles.find { it.id.toString() == searchTerm || it.nombres.contains(searchTerm, true) || it.apellidos.contains(searchTerm, true) }
                if (profileToAddHobby != null) {
                    print("Ingrese el título del hobby: ")
                    val titulo = readLine()!!
                    print("Ingrese la descripción del hobby: ")
                    val descripcion = readLine()!!
                    print("Ingrese la URL de la foto del hobby (puede ser nulo): ")
                    val urlPhoto = readLine()

                    val newHobby = Hobby(titulo, descripcion, urlPhoto)
                    profileToAddHobby.agregarHobby(newHobby)
                    println("Hobby agregado exitosamente al perfil con ID ${profileToAddHobby.id}.")
                } else {
                    println("No se encontró un perfil con la información ingresada.")
                }
            }
            5 -> {
                println("Saliendo del programa...")
            }
            else -> {
                println("Opción inválida. Intente nuevamente.")
            }
        }

    } while (opcion != 5)
}
