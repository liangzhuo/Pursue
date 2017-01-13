package upload;

import java.io.File;

import net.sf.jmimemagic.Magic;
import net.sf.jmimemagic.MagicException;
import net.sf.jmimemagic.MagicMatch;
import net.sf.jmimemagic.MagicMatchNotFoundException;
import net.sf.jmimemagic.MagicParseException;

import org.junit.Test;

public class FileType {
	/**
	 * 判断文件 消息内容类型
	 * @throws MagicParseException
	 * @throws MagicMatchNotFoundException
	 * @throws MagicException
	 */
	@Test
	public void fileType() throws MagicParseException, MagicMatchNotFoundException, MagicException {
		File file=new File("D:\\fg757p.exe");  
        Magic parser = new Magic();    
        MagicMatch match = parser.getMagicMatch(file, false);    
        System.out.println(match.getMimeType()) ;   
	}
}
