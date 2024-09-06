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
import com.example.demo.service.ClienteService;

import jakarta.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping(value = "gerente")
public class WebGerente {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private ContratoRepository contratoRepository;

	@Autowired
	private ClienteService clienteService;

	@GetMapping("/index")
	public String adminIndexTemplate(Model model, HttpSession session) {
		// Obtener el usuario logeado de la sesión
		Usuario usuario = (Usuario) session.getAttribute("usuarioLogeado");

		// Verificar si el usuario está logeado antes de agregarlo al modelo
		if (usuario != null) {
			model.addAttribute("nombre", usuario.getNombre());
		}
		return "index-gerente";
	}

	/* CRUD CLIENTE */

	@GetMapping("/crear-cliente")
	public String clienteCrearTemplate(Model model) {
		model.addAttribute("cliente", new Cliente());
		return "cliente-form";
	}

	@PostMapping("/save-cliente")
	public String clienteSaveProcess(@ModelAttribute("cliente") Cliente cliente) {
		if (cliente.getId().isEmpty()) {
			cliente.setId(null);
		}
		clienteRepository.save(cliente);
		return "redirect:/gerente/index";
	}

	@GetMapping("/lista-cliente")
	public String clienteListTemplate(Model model) {
		List<Cliente> clientesHabilitados = clienteService.obtenerClientesHabilitados();
		model.addAttribute("cliente", clientesHabilitados);
		return "cliente-lista";
	}

	@GetMapping("/edit-cliente/{id}")
	public String clienteEditTemplate(@PathVariable("id") String id, Model model) {
		model.addAttribute("cliente",
				clienteRepository.findById(id).orElseThrow(() -> new NotFoundException("cliente no encontrada")));
		return "cliente-form";
	}

	/* CRUD CLIENTE */

	@GetMapping("/crear-contrato")
	public String contratoCrearTemplate(Model model, HttpSession session) {
		Usuario usuario = (Usuario) session.getAttribute("usuarioLogeado");

		// Verificar si el usuario está logeado antes de agregarlo al modelo
		if (usuario != null) {
			model.addAttribute("id", usuario.getId());
		}
		model.addAttribute("contrato", new Contrato());
		model.addAttribute("usuario", usuarioRepository.findAll());
		List<Cliente> clientesHabilitados = clienteService.obtenerClientesHabilitados();
		model.addAttribute("cliente", clientesHabilitados);
		return "contrato-form";
	}

	@PostMapping("/save-contrato")
	public String contratoSaveProcess(@ModelAttribute("contrato") Contrato contrato) {
		if (contrato.getId().isEmpty()) {
			contrato.setId(null);
		}
		contratoRepository.save(contrato);
		return "redirect:/gerente/index";
	}

	@GetMapping("/lista-contrato")
	public String contratoListTemplate(Model model) {
		model.addAttribute("contrato", contratoRepository.findAll());
		return "contrato-lista";
	}

	@GetMapping("/edit-contrato/{id}")
	public String contratoEditTemplate(@PathVariable("id") String id, Model model) {
		model.addAttribute("contrato",
				contratoRepository.findById(id).orElseThrow(() -> new NotFoundException("contrato no encontrado")));
		model.addAttribute("usuario", usuarioRepository.findAll());
		model.addAttribute("cliente", clienteRepository.findAll());
		return "contrato-form-edit";
	}
}
