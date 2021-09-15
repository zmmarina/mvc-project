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
import com.gft.desafiomvc.entities.Vacinacao;
import com.gft.desafiomvc.services.LocalVacinacaoService;
import com.gft.desafiomvc.services.LoteVacinaService;
import com.gft.desafiomvc.services.PessoaService;
import com.gft.desafiomvc.services.VacinaService;
import com.gft.desafiomvc.services.VacinacaoService;

@Controller
@RequestMapping("/vacinacao")
public class VacinacaoController {

	@Autowired
	VacinacaoService vacinacaoService;

	@Autowired
	LoteVacinaService loteVacinaService;

	@Autowired
	LocalVacinacaoService localVacinacaoService;

	@Autowired
	PessoaService pessoaService;
	
	@Autowired
	VacinaService vacinaService;

	@RequestMapping(method = RequestMethod.GET, path = "/novo")
	public ModelAndView criarVacinacao(String cpf) {

		ModelAndView mv = new ModelAndView("vacinacao/form.html");
		
		mv.addObject("vacinacao", new Vacinacao());		
		mv.addObject("listalote", loteVacinaService.listarLoteVacina());
		mv.addObject("listalocal", localVacinacaoService.listarLocalVacinacao());
		
		if(!vacinacaoService.listarVacinacaoPessoaId(cpf).isEmpty()) {
			
			return mv.addObject("mensagem", "Erro: paciente já possui agendamento!");			
		}
		
		if(cpf != null && pessoaService.encontrarPessoaCPF(cpf) == null) {
			return mv.addObject("mensagem", "Por favor, digite um CPF válido.");
		}
		
		mv.addObject("pessoacpf", pessoaService.encontrarPessoaCPF(cpf));

		return mv;
	}

	@RequestMapping(method = RequestMethod.POST, path = "/novo")
	public ModelAndView salvarVacinacao(@Valid Vacinacao vacinacao, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

		ModelAndView mv = new ModelAndView("redirect:/vacinacao");

		boolean novaVacinacao = true;

		if (vacinacao.getId() != null) {
			novaVacinacao = false;
		}

		if (bindingResult.hasErrors()) {
			mv.setViewName("vacinacao/form.html");
			mv.addObject("vacinacao", vacinacao);
			return mv;
		}
		
		Vacinacao vacinacaoSalva = vacinacaoService.salvarVacinacao(vacinacao);
		
		try {
			LoteVacina loteReduzido= loteVacinaService.encontrarloteVacina(vacinacao.getLoteVacina().getId());
			loteReduzido.setQuantidadeRestante(loteReduzido.getQuantidadeRestante() - 1);		
			loteVacinaService.salvarLoteVacina(loteReduzido);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (novaVacinacao) {
			mv.addObject("vacinacao", new Vacinacao());
		} else {
			mv.addObject("vacinacao", vacinacaoSalva);
		}
					
		
		redirectAttributes.addFlashAttribute("mensagem", "Successo: agendamento de vacinação realizado!");

		return mv;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView cadastrarVacinacao(String cpf) {

		ModelAndView mv = new ModelAndView("vacinacao/list.html");

		if (cpf != null) {
			if(!vacinacaoService.listarVacinacaoPessoaId(cpf).isEmpty()) {
				mv.addObject("listapessoacpf", vacinacaoService.listarVacinacaoPessoaId(cpf));
			}else {
				ModelAndView mv2 = new ModelAndView("redirect:/vacinacao/novo?cpf=" + cpf);
				return mv2;
			}
		} else {			
			mv.addObject("lista", vacinacaoService.listarVacinacao());
		}

		return mv;
		
	}


	@RequestMapping(path = "/edit")
	public ModelAndView editarVacinacao(@RequestParam Long id) {

		ModelAndView mv = new ModelAndView("vacinacao/editar.html");
		
		Vacinacao vacinacao;

		try {
			vacinacao = vacinacaoService.encontrarVacinacao(id);
			mv.addObject("vacinacao", vacinacao);
			mv.addObject("lista", vacinacaoService.listarVacinacao());
			mv.addObject("listalote", loteVacinaService.listarLoteVacina());
			mv.addObject("listalocal", localVacinacaoService.listarLocalVacinacao());
			mv.addObject("listapessoaid", pessoaService.encontrarPessoa(vacinacao.getPessoa().getId()));

		} catch (Exception e) {
			vacinacao = new Vacinacao();
			mv.addObject("mensagem", e.getMessage());
		}

		
		return mv;
	}
	
	@RequestMapping(path = "/segunda")
	public ModelAndView segundaVacinacao(@RequestParam Long id) {

		
		ModelAndView mv = new ModelAndView("vacinacao/segunda.html");

		try {
			Vacinacao vacinacao = vacinacaoService.encontrarVacinacao(id);
			mv.addObject("vacinacao", vacinacao);
		} catch (Exception e) {
			e.printStackTrace();
		}

		mv.addObject("lista", vacinacaoService.listarVacinacao());
		mv.addObject("listalote", loteVacinaService.listarLoteVacina());
		mv.addObject("listalocal", localVacinacaoService.listarLocalVacinacao());
		mv.addObject("listapessoa", pessoaService.listarPessoas());
		mv.addObject("listavacina", vacinaService.listarVacinas());

		return mv;
	}

	@RequestMapping(method = RequestMethod.GET, path = "/delete")
	public ModelAndView deletarVacinacao(@RequestParam Long id, RedirectAttributes redirectAttributes) {

		ModelAndView mv = new ModelAndView("redirect:/vacinacao");

		try {
			vacinacaoService.excluirVacinacao(id);
			redirectAttributes.addFlashAttribute("mensagem", "Successo: vacinação excluída!");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("mensagem", "Erro ao excluir vacinação.");
		}

		return mv;
	}
}
