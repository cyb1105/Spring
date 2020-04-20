package myspring.user.test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import myspring.user.dao.mapper.StudentMapper;
import myspring.user.service.UserService;
import myspring.user.vo.DeptVO;
import myspring.user.vo.StudentVO;
import myspring.user.vo.UserVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/spring_beans.xml")
public class MybatisTest {
	private static final Logger logger = LogManager.getLogger();
	
	@Autowired
	DataSource dataSource;

	@Autowired
	SqlSessionFactory SqlSessionFactory;

	@Autowired
	SqlSession sqlSession;
	
	@Autowired
	UserService userService;
	
	@Autowired
	StudentMapper studentMapper;
	
	@Test
	public void stuMapper() {
		//Test���̽� : StudentMapper -> SqlSession -> StudentMapper.xml
		StudentVO student = new StudentVO(1500, "�Ѹ�", 20, "1�г�", "�ְ�", new DeptVO(20)); 
		int cnt = studentMapper.insertStudent(student);
		System.out.println("��ϵ� �л� �Ǽ�" + cnt);
		
		List<StudentVO> selectStudentDeptById = studentMapper.selectStudentDeptById();
		for (StudentVO studentVO : selectStudentDeptById) {
			System.out.println(studentVO);
		}
	}
	
	@Test @Ignore
	public void service() {
		//userService -> UserDAO -> SqlSession -> SqlSessionFactory -> DataSource
		UserVO user = userService.getUser("gildong");
		System.out.println(user);
	}

	@Test @Ignore
	public void sql2() {
		 //SqlSession -> SqlSessionFactory -> DataSource
		// Ư�� user�� update
		UserVO updateUser = new UserVO("java", "�ڹ�2", "��2", "����2");
		int cnt = sqlSession.update("userNS.updateUser", updateUser);
		logger.info(">>>>update cnt " + cnt);
//		System.out.println("update cnt " + cnt);
		
		List<UserVO> selectList = sqlSession.selectList("userNS.selectUserList");
		for (UserVO userVO : selectList) {
			logger.debug(">>>>userVO");
		}
	}

	@Test @Ignore
	public void sql() {
		// sqlSession�� selectOne()
		UserVO user = sqlSession.selectOne("userNS.selectUserById", "gildong");
		System.out.println(user);

		UserVO insertUser = new UserVO("cyb", "�ڹ�", "��", "����");
		int cnt = sqlSession.insert("insertUser", insertUser);
		System.out.println("��ϵ� �Ǽ� : " + cnt);
	}

	@Test
	@Ignore
	public void mybatis_spring() {
		System.out.println(SqlSessionFactory.getClass().getName());
		System.out.println(sqlSession.getClass().getName());
	}

	// DateSource dataSource = new
	@Test
	@Ignore
	public void con() {
		try {
			Connection con = dataSource.getConnection();
			System.out.println(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
