package service;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import model.Command;
import model.MemberDao;
import model.MemberDto;

public class MemberUpdate implements Command {

	@Override
	public void doCommand(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String userid = (String) session.getAttribute("userid");
		
		if (userid == null) {
			response.sendRedirect(request.getContextPath() + "/member/login.do");
			return;
		}
		
		MemberDao dao = new MemberDao();
		
		// 1. GETリクエストの場合 (修正画面表示用データ取得)
		if (request.getMethod().equalsIgnoreCase("GET")) {
			MemberDto dto = dao.searchUserId(userid);
			request.setAttribute("member", dto);
			return;
		}
		
		// 2. POSTリクエストの場合 (修正処理)
		request.setCharacterEncoding("UTF-8");
		
		// ファイルアップロード設定
		String savePath = request.getServletContext().getRealPath("upload/profile");
		File uploadDir = new File(savePath);
		if (!uploadDir.exists()) {
			uploadDir.mkdirs();
		}
		
		int maxSize = 5 * 1024 * 1024; // 5MB
		String encoding = "UTF-8";
		
		MultipartRequest multi = new MultipartRequest(
			request, 
			savePath, 
			maxSize, 
			encoding, 
			new DefaultFileRenamePolicy()
		);
		
		MemberDto dto = dao.searchUserId(userid); // 既存データの取得
		dto.setNickname(multi.getParameter("nickname"));
		dto.setEmail(multi.getParameter("email"));
		dto.setPhone(multi.getParameter("phone"));
		dto.setLastNameKanji(multi.getParameter("lastNameKanji"));
		dto.setFirstNameKanji(multi.getParameter("firstNameKanji"));
		dto.setLastNameKana(multi.getParameter("lastNameKana"));
		dto.setFirstNameKana(multi.getParameter("firstNameKana"));
		
		// 新しい画像がアップロードされた場合
		String fileName = multi.getFilesystemName("profileImg");
		if (fileName != null) {
			// 以前の画像を削除 (デフォルト画像でない場合)
			if (dto.getProfileImg() != null && !dto.getProfileImg().isEmpty()) {
				File oldFile = new File(savePath + File.separator + dto.getProfileImg());
				if (oldFile.exists()) {
					oldFile.delete();
				}
			}
			dto.setProfileImg(fileName);
		}
		
		int result = dao.updateMember(dto);
		
		response.setContentType("text/html; charset=UTF-8");
		if (result > 0) {
			// セッションのニックネームも更新
			session.setAttribute("nickname", dto.getNickname());
			session.setAttribute("profileImg", dto.getProfileImg()); // セッションにも保存
			response.getWriter().write("<script>alert('会員情報が修正されました。'); location.href='" + request.getContextPath() + "/member/mypage.do';</script>");
		} else {
			response.getWriter().write("<script>alert('修正に失敗しました。'); history.back();</script>");
		}
	}
}
