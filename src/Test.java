import com.sjl.joinme.activity_images.ActivityImagesDAO;
import com.sjl.joinme.activity_images.ActivityImagesDTO;

public class Test {
	public static void main(String[] args) {
		ActivityImagesDTO dto = new ActivityImagesDTO();
		ActivityImagesDAO dao = new ActivityImagesDAO();
		dto.setActivity_id(5);
		// dto.setCreated_datetime("created_datetime");
		dto.setDescription("description");
		// dto.setImage("image");
		dao.addActivityImages(dto);
		System.out.println("ccccc");
		System.out.println("lok");
		System.out.println("jit");
	}

}
