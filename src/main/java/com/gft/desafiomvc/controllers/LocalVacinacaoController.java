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

import com.gft.desafiomvc.entities.LocalVacinacao;
import com.gft.desafiomvc.services.LocalVacinacaoService;

@Controller
@RequestMapping("/localvacinacao")
public class LocalVacinacaoController {
	
	@Autowired
	private LocalVacinacaoService localVacinacaoService;
	
	@RequestMapping(method= RequestMethod.GET, path= "/novo")
	public ModelAndView criarLocalVacinacao() {
		
		ModelAndView mv = new ModelAndView("localvacinacao/form.html");
		mv.addObject("localvacinacao", new LocalVacinacao());
				
		return mv;
	}
	
	@RequestMapping(method= RequestMethod.POST, path= "/novo")
	public ModelAndView salvarLocalVacinacao(@Valid LocalVacinacao localVacinacao, BindingResult bindingResult) {
		
		ModelAndView mv = new ModelAndView("localvacinacao/form.html");
		
		boolean novoLocalVacinacao = true;
		
		if(localVacinacao.getId() != null) {
			novoLocalVacinacao = false;
		}
		
		if(bindingResult.hasErrors()) {
			mv.addObject("localvacinacao", localVacinacao);
			return mv;
		}		
		
		LocalVacinacao localVacinacaoSalvo = localVacinacaoService.salvarLocalVacinacao(localVacinacao);
		
		if(novoLocalVacinacao) {
			mv.addObject("localvacinacao", new LocalVacinacao());
		} else {
			mv.addObject("localvacinacao", localVacinacaoSalvo);
		}
		
		mv.addObject("mensagem", "Successo: local de vacinação salvo!");
		
		return mv;		
	}
	
	
	@RequestMapping(method= RequestMethod.GET)
	public ModelAndView listarLocalVacinacao() {
		
		ModelAndView mv = new ModelAndView("localvacinacao/list.html");
		mv.addObject("lista", localVacinacaoService.listarLocalVacinacao());
		
		return mv;
	}
	
	@RequestMapping("/edit")
	public ModelAndView editarLocalVacinacao(@RequestParam Long id) {
		
		ModelAndView mv = new ModelAndView("localvacinacao/form.html");
		LocalVacinacao localVacinacao;
		
		try {
			localVacinacao = localVacinacaoService.encontrarLocalVacinacaoa(id);
		}catch(Exception e) {
			localVacinacao = new LocalVacinacao();
			mv.addObject("mensagem", e.getMessage());
		}
		
		mv.addObject("localvacinacao", localVacinacao);	
				
		return mv;
	}
	
	@RequestMapping("/delete")
	public ModelAndView deletarLocalVacinacao(@RequestParam Long id, RedirectAttributes redirectAttributes) {
		
		ModelAndView mv = new ModelAndView("redirect:/localvacinacao");
				
		try {
			localVacinacaoService.excluirLocalVacinacao(id);
			redirectAttributes.addFlashAttribute("mensagem", "Successo: local de vacinação excluído!");
		}catch(Exception e) {
			redirectAttributes.addFlashAttribute("mensagem", "Erro ao excluir local de vacinação!" + e.getMessage());
		}
			
		return mv;
	}
}
