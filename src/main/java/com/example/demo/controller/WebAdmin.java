package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Cliente;
import com.example.demo.entity.Contrato;
import com.example.demo.entity.Usuario;
import com.example.demo.exception.NotFoundException;
import com.example.demo.repository.ClienteRepository;
import com.example.demo.repository.ContratoRepository;
import com.example.demo.repository.UsuarioRepository;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "admin")
public class WebAdmin {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private ContratoRepository contratoRepository;

	@GetMapping("/index")
	public String adminIndexTemplate(Model model, HttpSession session) {
		// Obtener el usuario logeado de la sesión
		Usuario usuario = (Usuario) session.getAttribute("usuarioLogeado");

		// Verificar si el usuario está logeado antes de agregarlo al modelo
		if (usuario != null) {
			model.addAttribute("nombre", usuario.getNombre());
		}
		return "index-admin";
	}

	/* CRUD USUARIO */

	@GetMapping("/crear-usuario")
	public String usuarioCrearTemplate(Model model) {
		model.addAttribute("usuario", new Usuario());
		return "usuario-form";
	}

	@PostMapping("/save-usuario")
	public String usuarioSaveProcess(@ModelAttribute("usuario") Usuario usuario) {
		if (usuario.getId().isEmpty()) {
			usuario.setId(null);
		}
		usuarioRepository.save(usuario);
		return "redirect:/admin/index";
	}

	@GetMapping("/lista-usuario")
	public String usuarioListTemplate(Model model) {
		model.addAttribute("usuario", usuarioRepository.findAll());
		return "usuario-lista";
	}

	@GetMapping("/edit-usuario/{id}")
	public String usuarioEditTemplate(@PathVariable("id") String id, Model model) {
		model.addAttribute("usuario",
				usuarioRepository.findById(id).orElseThrow(() -> new NotFoundException("usuario no encontrada")));
		return "usuario-edit";
	}

	@GetMapping("/estado-usuario/{id}")
	public String usuarioEstadoProcess(@PathVariable("id") String id, Model model) {
		model.addAttribute("usuario",
				usuarioRepository.findById(id).orElseThrow(() -> new NotFoundException("usuario no encontrada")));
		return "usuario-estado";
	}

	/* CLIENTES */
	@GetMapping("/lista-clientes")
	public String clienteListTemplate(Model model) {
		model.addAttribute("cliente", clienteRepository.findAll());
		return "cliente-lista-admin";
	}

	@GetMapping("/estado-cliente/{id}")
	public String clienteEstadoProcess(@PathVariable("id") String id, Model model) {
		model.addAttribute("cliente",
				clienteRepository.findById(id).orElseThrow(() -> new NotFoundException("cliente no encontrado")));
		return "cliente-estado";
	}

	@PostMapping("/save-cliente")
	public String clienteSaveProcess(@ModelAttribute("cliente") Cliente cliente) {
		if (cliente.getId().isEmpty()) {
			cliente.setId(null);
		}
		clienteRepository.save(cliente);
		return "redirect:/admin/index";
	}

	/* CONTRATO */
	@GetMapping("/lista-contrato")
	public String contratoEstadoTemplate(Model model) {
		model.addAttribute("contrato", contratoRepository.findAll());
		return "contrato-lista-admin";
	}

	@GetMapping("/estado-contrato/{id}")
	public String contratoDeleteProcess(@PathVariable("id") String id,Model model) {
	model.addAttribute("cliente", clienteRepository.findAll());
		model.addAttribute("contrato",
				contratoRepository.findById(id).orElseThrow(() -> new NotFoundException("contrato no encontrado")));
		return "contrato-estado";
	}
	
	@PostMapping("/save-contrato")
	public String contratoSaveProcess(@ModelAttribute("contrato") Contrato contrato) {
		if (contrato.getId().isEmpty()) {
			contrato.setId(null);
		}
		contratoRepository.save(contrato);
		return "redirect:/admin/index";
	}
}
