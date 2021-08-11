package com.gft.desafiomvc.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gft.desafiomvc.entities.Pessoa;
import com.gft.desafiomvc.services.PessoaService;

@Controller
@RequestMapping("/paciente")
public class PessoaController {
	
	@Autowired
	private PessoaService pessoaService;
	
	@RequestMapping(method= RequestMethod.GET, path= "/novo")
	public ModelAndView criarPessoa() {
		
		ModelAndView mv = new ModelAndView("pessoa/form.html");
		mv.addObject("pessoa", new Pessoa());
				
		return mv;
	}
	
	@RequestMapping(method= RequestMethod.POST, path= "/novo")
	public ModelAndView salvarPessoa(@Valid Pessoa pessoa, BindingResult bindingResult) {
		
		ModelAndView mv = new ModelAndView("pessoa/form.html");
		
		boolean novaPessoa = true;
		
		if(pessoa.getId() != null) {
			novaPessoa = false;
		}
		
		if(bindingResult.hasErrors()) {
			mv.addObject("pessoa", pessoa);
			return mv;
		}		
		
		Pessoa pessoaSalva = pessoaService.salvarPessoa(pessoa);
		
		if(novaPessoa) {
			mv.addObject("pessoa", new Pessoa());
		} else {
			mv.addObject("pessoa", pessoaSalva);
		}
		
		mv.addObject("mensagem", "Successo: paciente salvo!");
		
		return mv;		
	}
	
	
	@RequestMapping(method= RequestMethod.GET)
	public ModelAndView listarPessoas() {
		
		ModelAndView mv = new ModelAndView("pessoa/list.html");
		mv.addObject("lista", pessoaService.listarPessoas());
		
		return mv;
	}
	
	@RequestMapping("/edit")
	public ModelAndView editarPessoa(@RequestParam Long id) {
		
		ModelAndView mv = new ModelAndView("pessoa/form.html");
		Pessoa pessoa;
		
		try {
			pessoa = pessoaService.encontrarPessoa(id);
		}catch(Exception e) {
			pessoa = new Pessoa();
			mv.addObject("mensagem", e.getMessage());
		}
		
		mv.addObject("pessoa", pessoa);	
				
		return mv;
	}
	
	@RequestMapping("/delete")
	public ModelAndView deletarPessoa(@RequestParam Long id, RedirectAttributes redirectAttributes) {
		
		ModelAndView mv = new ModelAndView("redirect:/paciente");
				
		try {
			pessoaService.excluirPessoa(id);
			redirectAttributes.addFlashAttribute("mensagem", "Successo: paciente excluído!");
		}catch(Exception e) {
			redirectAttributes.addFlashAttribute("mensagem", "Erro ao excluir paciente!" + e.getMessage());
		}
			
		return mv;
	}
}
