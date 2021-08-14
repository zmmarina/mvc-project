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

import com.gft.desafiomvc.entities.LoteVacina;
import com.gft.desafiomvc.services.LoteVacinaService;
import com.gft.desafiomvc.services.VacinaService;

@Controller
@RequestMapping("/lotevacina")
public class LoteVacinaController {
	
	@Autowired
	LoteVacinaService loteVacinaService;	
	
	@Autowired
	VacinaService vacinaService;	
	
		
	@RequestMapping(method= RequestMethod.GET, path="/novo")
	public ModelAndView criarLoteVacina() {
		
		ModelAndView mv = new ModelAndView("lotevacina/form.html");
		mv.addObject("lotevacina", new LoteVacina());
		mv.addObject("lista", vacinaService.listarVacinas());
		
		return mv;
	}
	
	@RequestMapping(method= RequestMethod.POST, path= "/novo")
	public ModelAndView salvarLoteVacina(@Valid LoteVacina loteVacina, BindingResult bindingResult) {
		
		ModelAndView mv = new ModelAndView("lotevacina/form.html");
		
		boolean novoLoteVacina = true;
		
		if(loteVacina.getId() != null) {
			novoLoteVacina = false;
		}
		
		if(bindingResult.hasErrors()) {
			mv.addObject("lotevacina", loteVacina);
			return mv;
		}
		
		LoteVacina loteVacinaSalvo = loteVacinaService.salvarLoteVacina(loteVacina);
		
		if(novoLoteVacina) {
			mv.addObject("lotevacina", new LoteVacina());
		} else {
			mv.addObject("lotevacina", loteVacinaSalvo);
		}
		
		mv.addObject("mensagem", "Successo: lote de vacina salvo!");
		
		return mv;
	}
	
	@RequestMapping(method= RequestMethod.GET)
	public ModelAndView listarLoteVacinas() {
		
		ModelAndView mv = new ModelAndView("lotevacina/list.html");
		mv.addObject("lista", loteVacinaService.listarLoteVacina());
		
		return mv;
	}
	
	@RequestMapping(path="/edit")
	public ModelAndView editarLoteVacina(@RequestParam Long id) {
		
		ModelAndView mv = new ModelAndView("lotevacina/form.html");
		
		try {
			LoteVacina loteVacina = loteVacinaService.encontrarloteVacina(id);
			mv.addObject("lotevacina", loteVacina);
		} catch (Exception e) {
		 	e.printStackTrace();
		}
		
		mv.addObject("lista", vacinaService.listarVacinas());
		
		return mv;
	}
	
	
	
	
	@RequestMapping(method= RequestMethod.GET, path="/delete")
	public ModelAndView deletarLoteVacina(@RequestParam Long id, RedirectAttributes redirectAttributes) {
		
		ModelAndView mv = new ModelAndView("redirect:/lotevacina");
		
		try {
			loteVacinaService.excluirLoteVacina(id);
			redirectAttributes.addFlashAttribute("mensagem", "Successo: lote de vacina excluído");
		}catch(Exception e) {
			redirectAttributes.addFlashAttribute("mensagem", "Erro ao excluir lote de vacina.");
		}			
		
		return mv;
	}
	


}
