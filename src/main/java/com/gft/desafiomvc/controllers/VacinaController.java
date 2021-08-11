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

import com.gft.desafiomvc.entities.Vacina;
import com.gft.desafiomvc.services.VacinaService;


@Controller
@RequestMapping("/vacina")
public class VacinaController {
	
	@Autowired
	private VacinaService vacinaService;
	
	@RequestMapping(method= RequestMethod.GET, path= "/novo")
	public ModelAndView criarVacina() {
		
		ModelAndView mv = new ModelAndView("vacina/form.html");
		mv.addObject("vacina", new Vacina());
				
		return mv;
	}
	
	@RequestMapping(method= RequestMethod.POST, path= "/novo")
	public ModelAndView salvarVacina(@Valid Vacina vacina, BindingResult bindingResult) {
		
		ModelAndView mv = new ModelAndView("vacina/form.html");
		
		boolean novaVacina = true;
		
		if(vacina.getId() != null) {
			novaVacina = false;
		}
		
		if(bindingResult.hasErrors()) {
			mv.addObject("vacina", vacina);
			return mv;
		}
		
		Vacina vacinaSalva = vacinaService.salvarVacina(vacina);
		
		if(novaVacina) {
			mv.addObject("vacina", new Vacina());
		} else {
			mv.addObject("vacina", vacinaSalva);
		}
		
		mv.addObject("mensagem", "Successo: vacina salva!");
		
		return mv;		
	}
	
	
	@RequestMapping(method= RequestMethod.GET)
	public ModelAndView listarVacinas() {
		
		ModelAndView mv = new ModelAndView("vacina/list.html");
		mv.addObject("lista", vacinaService.listarVacinas());
		
		return mv;
	}
	
	@RequestMapping("/edit")
	public ModelAndView editarVacina(@RequestParam Long id) {
		
		ModelAndView mv = new ModelAndView("vacina/form.html");
		Vacina vacina;
		
		try {
			vacina = vacinaService.encontrarVacina(id);
		}catch(Exception e) {
			vacina = new Vacina();
			mv.addObject("mensagem", e.getMessage());
		}
		
		mv.addObject("vacina", vacina);	
				
		return mv;
	}
	
	@RequestMapping("/delete")
	public ModelAndView deletarVacina(@RequestParam Long id, RedirectAttributes redirectAttributes) {
		
		ModelAndView mv = new ModelAndView("redirect:/vacina");
				
		try {
			vacinaService.excluirVacina(id);
			redirectAttributes.addFlashAttribute("mensagem", "Successo: vacina excluída!");
		}catch(Exception e) {
			redirectAttributes.addFlashAttribute("mensagem", "Erro ao excluir vacina!" + e.getMessage());
		}
			
		return mv;
	}
	
}
