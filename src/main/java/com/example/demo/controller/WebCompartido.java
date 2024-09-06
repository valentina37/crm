package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Usuario;
import com.example.demo.repository.UsuarioRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class WebCompartido {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@GetMapping("/")
	public String compartidoIndexTemplate(Model model) {
		return "index";
	}

	@PostMapping("/logear")
	public String adminLogearTemplate(@RequestParam String user, @RequestParam String contrasena, Model model,
			HttpSession session) {
		// Buscar al coordinador por nombre de usuario en la base de datos
		Usuario usuarios = null;
		for (Usuario u : usuarioRepository.findAll()) {
			if (u.getUser().equals(user)) {
				usuarios = u;
				break;
			}
		}

		// Verificar si se encontró al coordinador y si la contraseña es correcta
		if (usuarios != null && usuarios.getContrasena().equals(contrasena) && usuarios.getEstado().equals("Habilitado")
				&& usuarios.getRol().equals("Admin")) {
			// Guardar el usuario logeado en la sesión
			session.setAttribute("usuarioLogeado", usuarios);
			// Si las credenciales son correctas, redirigir a la página de inicio
			return "redirect:/admin/index";
		} else {
			if (usuarios != null && usuarios.getContrasena().equals(contrasena)
					&& usuarios.getEstado().equals("Habilitado") && usuarios.getRol().equals("Gerente")) {
				// Guardar el usuario logeado en la sesión
				session.setAttribute("usuarioLogeado", usuarios);
				// Si las credenciales son correctas, redirigir a la página de inicio
				return "redirect:/gerente/index";
			} else {
				if (usuarios != null && usuarios.getContrasena().equals(contrasena)
						&& usuarios.getEstado().equals("Habilitado") && usuarios.getRol().equals("Empleado")) {
					// Guardar el usuario logeado en la sesión
					session.setAttribute("usuarioLogeado", usuarios);
					// Si las credenciales son correctas, redirigir a la página de inicio
					return "redirect:/empleado/index";
				} else {
					// Si las credenciales son incorrectas, mostrar un mensaje de error y volver al
					// formulario de login
					model.addAttribute("error", true);
					return "index";
				}
			}
		}
	}
}
