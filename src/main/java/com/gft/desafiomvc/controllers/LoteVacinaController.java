package com.gft.desafiomvc.controllers;

import java.util.ArrayList;

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
	public ModelAndView salvarLoteVacina(@Valid LoteVacina loteVacina, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		
		ModelAndView mv = new ModelAndView("redirect:/lotevacina");
				
		boolean novoLoteVacina = true;
		
		if(loteVacina.getId() != null) {
			novoLoteVacina = false;
		}
		
		if(bindingResult.hasErrors()) {
			mv.setViewName("lotevacina/form.html");
			mv.addObject("lotevacina", loteVacina);
			return mv;
		}		
		
		try {
			LoteVacina loteVacinaSalvo = loteVacinaService.salvarLoteVacina(loteVacina);
			mv.addObject("lotevacina", loteVacinaSalvo);
			redirectAttributes.addFlashAttribute("mensagem", "Sucesso: dados salvos!");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("mensagem", "Lote de Vacina já cadastrado!");
			e.printStackTrace();
		}
		
		if(novoLoteVacina) {
			mv.addObject("lotevacina", novoLoteVacina);
		}		
						
		return mv;		
	}
	
	@RequestMapping(method= RequestMethod.GET)
	public ModelAndView listarLoteVacinas() {
		
		ModelAndView mv = new ModelAndView("lotevacina/list.html");
		mv.addObject("lista", loteVacinaService.listarLoteVacina());
		
		return mv;
	}
	
	@RequestMapping(method= RequestMethod.GET, path="/relatorio")
	public ModelAndView gerarRelatorioLoteVacinas() {
		
		ArrayList<RelatorioVacinaDTO> listaRelatorio = new ArrayList<>(); 
		
		for (LoteVacina loteVacina : loteVacinaService.listarLoteVacina()) {
			
			RelatorioVacinaDTO relatorioVacina = new RelatorioVacinaDTO(loteVacina);	
			
			if(relatorioVacina.getDiasVencimento() < 30) {
				
				listaRelatorio.add(relatorioVacina);				
			}			
			
		}
		
		ModelAndView mv = new ModelAndView("lotevacina/relatorio.html");
		mv.addObject("lista", listaRelatorio);
		
		return mv;
	}
	
	@RequestMapping(path="/edit")
	public ModelAndView editarLoteVacina(@RequestParam Long id) {
		
		ModelAndView mv = new ModelAndView("lotevacina/editar.html");		
		LoteVacina loteVacina;
		
		try {
			loteVacina = loteVacinaService.encontrarloteVacina(id);			
		} catch (Exception e) {
			loteVacina = new LoteVacina();
			mv.addObject("mensagem", e.getMessage());
		}
		
		mv.addObject("lotevacina", loteVacina);
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
