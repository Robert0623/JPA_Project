package com.tour.app.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tour.app.config.auth.PrincipalDetails;

import com.tour.app.domain.MyBoard;
import com.tour.app.dto.TourReviewDTOInterface;
import com.tour.app.service.MyBoardService;

import com.tour.app.config.auth.PrincipalDetails;
import com.tour.app.domain.ReviewBoard;
import com.tour.app.dto.Area;
import com.tour.app.dto.TourReviewDTO;
import com.tour.app.dto.TourReviewDTOInterface;
import com.tour.app.service.TourReviewService;

import com.tour.app.config.auth.PrincipalDetails;
import com.tour.app.domain.TakeRoom;
import com.tour.app.repository.TakeRoomRepository;
import com.tour.app.service.TakeRoomService;

@RequestMapping("/myBoard/*")
@Controller
public class MyBoardController {	

	@Autowired
	private MyBoardService myBoardService;
	
	@Autowired
	private TourReviewService tourReviewService;
	
	@Autowired
	private TakeRoomService takeRoomService;
	
	@GetMapping("insert")
	@PreAuthorize("isAuthenticated()")
	public String insert() {
		return "/mypage/insert";
	}
	//????????? ??????
	@PostMapping("insert")
	public String insert(MyBoard myBoard, @AuthenticationPrincipal PrincipalDetails principal) {
		
		myBoardService.insert(myBoard, principal.getMember());
		return "redirect:/myBoard/list";
	}
	//?????????
	//????????? ???????????? 
//	@GetMapping("list")
//	@PreAuthorize("isAuthenticated()")
//	public String list(Model model) {
//		model.addAttribute("count", myBoardService.count());
//		model.addAttribute("myBoards", myBoardService.findAll());
//		return "/mypage/list"; //jsp??????
//	}
	//?????????
	//????????? ???????????? ?????????
	//????????? ?????? + ????????? ?????????????????? ?????? ????????? list.jsp???????????????.
//	@GetMapping("list")
//	@PreAuthorize("isAuthenticated()")
//	public String list(Model model, 
//			@PageableDefault(size=5, sort = "num", direction=Direction.DESC) Pageable pageable) {
//		Page<MyBoard> lists = myBoardService.findAll(pageable);
//		model.addAttribute("myBoards", lists);
//		model.addAttribute("count", myBoardService.count());
//		return "/mypage/list"; //jsp??????
//	}
	
	//????????? ???????????? 
//	@GetMapping("list")
//	@PreAuthorize("isAuthenticated()")
//	public String list(Model model) {
////		model.addAttribute("count", tourReviewService.count()); //count()?????????. ?????????????
//		model.addAttribute("tourReviews", tourReviewService.findAll());
//		return "/mypage/list"; //jsp??????
//	}
	

	
//	//?????? ?????? ?????????(ToueReviewController?????? ??????)
	@GetMapping("tourReviewList")
	@PreAuthorize("isAuthenticated()")
	public String list(Model model, @AuthenticationPrincipal PrincipalDetails principal) 
	{
//		model.addAttribute("reviewBoards", tourReviewService.findAll());
		model.addAttribute("reviewBoards", tourReviewService.findByUserid(principal.getMember().getUserid()));
		return "/mypage/tourReviewList"; //jsp
	}
	
	
	// ?????? ?????? ??? ???????????? 
	@GetMapping("tourreviewView/{num}")
	public String view(@PathVariable int num ,Model model)
	{
		model.addAttribute("reviewBoard", tourReviewService.findById(num));

		return "/mypage/tourreviewView";
	}
	
	//?????? ??? ?????? ?????? ??? ?????????
  	@GetMapping("takeRoomList")
	@PreAuthorize("isAuthenticated()")

  	public String list2(Model model) {
  
  		model.addAttribute("takerooms",takeRoomService.findAll());	


  		return "/mypage/takeRoomList";  //jsp
  	 }	
//	//?????? ?????? ?????????(ToueReviewController?????? ??????)
//	@GetMapping("tourReviewList")
//	@PreAuthorize("isAuthenticated()")
//	public String list(Model model , HttpServletRequest request) 
//	{
//		model.addAttribute("reviewBoards", tourReviewService.findAll());
////		int contentsid = (int)request.getSession().getAttribute("tourarea_contentsId");
////		
////		List<TourReviewDTOInterface> result=tourReviewService.findReviewBoardList(contentsid);
////		model.addAttribute("reviewBoards", result);
//		return "/mypage/tourReviewList"; //jsp
//	}
	
	
	
	
	//?????? ??? ?????? ?????? ??? ????????????

}
