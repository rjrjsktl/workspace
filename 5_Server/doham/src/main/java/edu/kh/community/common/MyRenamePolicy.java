package edu.kh.community.common;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.oreilly.servlet.multipart.FileRenamePolicy;

// 파일명 변경 정책
public class MyRenamePolicy implements FileRenamePolicy {

	@Override
	public File rename(File originalFile) {

		long currentTime = System.currentTimeMillis();
		// 1970 1월 1일 오전 9시부터 현재 시간까지의 경과한 ms를 반환해줌

		SimpleDateFormat ft = new SimpleDateFormat("yyyyMMddHHmmss");

		int ranNum = (int)(Math.random()*100000);
		// 0 <= 난수 <= 99999;
		// user.png

		String str = "_" + String.format("%05d", ranNum );
		// String.format : 문자열을 지정된 패턴의 형식으로 변경하는 메서드
		// %05d : 오른쪽 정렬 된 십진 정수(d) 자리 형태로 변경. 빈자리는 0으로 채움

		// 파일명을 변경해도 확장자를 유지하기 위하여
		// 별도로 확장자명 가져오기

		String name = originalFile.getName();
		String ext = null;

		int dot = name.lastIndexOf(".");

		if(dot != -1) { 
			//dot 포함해서 확장자 추출

			ext = name.substring(dot);

		}else {
			ext = "";
		}

		String fileName = ft.format(new Date(currentTime)) + str + ext;
		  //20230330121230 +_01234+.png
		// 20230330121230_01234.png
		
		File newFile = new File(originalFile.getParent(),fileName );
			// 경로면 문자열을 반환 or 무모디렉토리의 이름을 지정하지 않으면 null을 반환한다.
	
		return newFile;

	}


}
