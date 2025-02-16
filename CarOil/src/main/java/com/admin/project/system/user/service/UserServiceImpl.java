package com.admin.project.system.user.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.admin.common.constant.UserConstants;
import com.admin.common.exception.BusinessException;
import com.admin.common.utils.StringUtils;
import com.admin.common.utils.security.ShiroUtils;
import com.admin.common.utils.text.Convert;
import com.admin.framework.aspectj.lang.annotation.DataScope;
import com.admin.framework.shiro.service.PasswordService;
import com.admin.project.system.config.service.IConfigService;
import com.admin.project.system.role.domain.Role;
import com.admin.project.system.role.mapper.RoleMapper;
import com.admin.project.system.user.domain.RegUser;
import com.admin.project.system.user.domain.User;
import com.admin.project.system.user.mapper.UserMapper;

/**
 * 用户 业务层处理
 * 
 * 
 */
@Service
public class UserServiceImpl implements IUserService {
	private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private RoleMapper roleMapper;



	@Autowired
	private IConfigService configService;

	@Autowired
	private PasswordService passwordService;

	/**
	 * 根据条件分页查询用户列表
	 * 
	 * @param user 用户信息
	 * @return 用户信息集合信息
	 */
	@Override
	@DataScope(deptAlias = "d", userAlias = "u")
	public List<User> selectUserList(User user) {
		// 生成数据权限过滤条件
		return userMapper.selectUserList(user);
	}

	/**
	 * 根据条件分页查询已分配用户角色列表
	 * 
	 * @param user 用户信息
	 * @return 用户信息集合信息
	 */
	@DataScope(deptAlias = "d", userAlias = "u")
	public List<User> selectAllocatedList(User user) {
		return userMapper.selectAllocatedList(user);
	}

	/**
	 * 根据条件分页查询未分配用户角色列表
	 * 
	 * @param user 用户信息
	 * @return 用户信息集合信息
	 */
	@DataScope(deptAlias = "d", userAlias = "u")
	public List<User> selectUnallocatedList(User user) {
		return userMapper.selectUnallocatedList(user);
	}

	/**
	 * 通过用户名查询用户
	 * 
	 * @param userName 用户名
	 * @return 用户对象信息
	 */
	@Override
	public User selectUserByLoginName(String userName) {
		return userMapper.selectUserByLoginName(userName);
	}

	/**
	 * 新增保存用户信息
	 * 
	 * @param user 用户信息
	 * @return 结果
	 */
	@Override
	@Transactional
	public int reg(RegUser user) {
//		Resident res = new Resident();
//
//		res.setType(user.getType());
//		res.setIdNum(user.getIdNum());

		user.randomSalt();
		user.setPassword(passwordService.encryptPassword(user.getLoginName(), user.getPassword(), user.getSalt()));
		user.setCreateBy("reg");
		user.setRoleId(102L);
		user.setStatus("0");
		// 新增用户信息
		int rows = userMapper.insertUser(user);

//		res.setUserId(user.getUserId());
//		residentSrv.insertResident(res);

		// 新增用户与角色管理
		// insertUserRole(user);
		return rows;
	}
	/**
	 * 通过手机号码查询用户
	 * 
	 * @param phoneNumber 手机号码
	 * @return 用户对象信息
	 */
	@Override
	public User selectUserByPhoneNumber(String phoneNumber) {
		return userMapper.selectUserByPhoneNumber(phoneNumber);
	}

	/**
	 * 通过邮箱查询用户
	 * 
	 * @param email 邮箱
	 * @return 用户对象信息
	 */
	@Override
	public User selectUserByEmail(String email) {
		return userMapper.selectUserByEmail(email);
	}

	/**
	 * 通过用户ID查询用户
	 * 
	 * @param userId 用户ID
	 * @return 用户对象信息
	 */
	@Override
	public User selectUserById(Long userId) {
		return userMapper.selectUserById(userId);
	}

	/**
	 * 通过用户ID删除用户
	 * 
	 * @param userId 用户ID
	 * @return 结果
	 */
	@Override
	public int deleteUserById(Long userId) {
		return userMapper.deleteUserById(userId);
	}

	/**
	 * 批量删除用户信息
	 * 
	 * @param ids 需要删除的数据ID
	 * @return 结果
	 */
	@Override
	public int deleteUserByIds(String ids) throws BusinessException {
		Long[] userIds = Convert.toLongArray(ids);
		for (Long userId : userIds) {
			User user = selectUserById(userId);
			checkUserAllowed(user);
		}
		return userMapper.deleteUserByIds(userIds);
	}

	/**
	 * 新增保存用户信息
	 * 
	 * @param user 用户信息
	 * @return 结果
	 */
	@Override
	@Transactional
	public int insertUser(User user) {
		user.randomSalt();
		user.setPassword(passwordService.encryptPassword(user.getLoginName(), user.getPassword(), user.getSalt()));
		user.setCreateBy(ShiroUtils.getLoginName());
		// 新增用户信息
		int rows = userMapper.insertUser(user);
		// 新增用户与角色管理
		// insertUserRole(user);
		return rows;
	}

	/**
	 * 修改保存用户信息
	 * 
	 * @param user 用户信息
	 * @return 结果
	 */
	@Override
	@Transactional
	public int updateUser(User user) {
		Long userId = user.getUserId();
		user.setUpdateBy(ShiroUtils.getLoginName());
		return userMapper.updateUser(user);
	}

	/**
	 * 修改用户个人详细信息
	 * 
	 * @param user 用户信息
	 * @return 结果
	 */
	@Override
	public int updateUserInfo(User user) {
		return userMapper.updateUser(user);
	}

	/**
	 * 修改用户密码
	 * 
	 * @param user 用户信息
	 * @return 结果
	 */
	@Override
	public int resetUserPwd(User user) {
		user.randomSalt();
		user.setPassword(passwordService.encryptPassword(user.getLoginName(), user.getPassword(), user.getSalt()));
		return updateUserInfo(user);
	}

	/**
	 * 校验登录名称是否唯一
	 * 
	 * @param loginName 用户名
	 * @return
	 */
	@Override
	public String checkLoginNameUnique(String loginName) {
		int count = userMapper.checkLoginNameUnique(loginName);
		if (count > 0) {
			return UserConstants.USER_NAME_NOT_UNIQUE;
		}
		return UserConstants.USER_NAME_UNIQUE;
	}

	/**
	 * 校验用户名称是否唯一
	 *
	 * @param user 用户信息
	 * @return
	 */
	@Override
	public String checkPhoneUnique(User user) {
		Long userId = StringUtils.isNull(user.getUserId()) ? -1L : user.getUserId();
		User info = userMapper.checkPhoneUnique(user.getPhonenumber());
		if (StringUtils.isNotNull(info) && info.getUserId().longValue() != userId.longValue()) {
			return UserConstants.USER_PHONE_NOT_UNIQUE;
		}
		return UserConstants.USER_PHONE_UNIQUE;
	}

	/**
	 * 校验email是否唯一
	 *
	 * @param user 用户信息
	 * @return
	 */
	@Override
	public String checkEmailUnique(User user) {
		Long userId = StringUtils.isNull(user.getUserId()) ? -1L : user.getUserId();
		User info = userMapper.checkEmailUnique(user.getEmail());
		if (StringUtils.isNotNull(info) && info.getUserId().longValue() != userId.longValue()) {
			return UserConstants.USER_EMAIL_NOT_UNIQUE;
		}
		return UserConstants.USER_EMAIL_UNIQUE;
	}

	/**
	 * 校验用户是否允许操作
	 * 
	 * @param user 用户信息
	 */
	public void checkUserAllowed(User user) {
		if (StringUtils.isNotNull(user.getUserId()) && user.isAdmin()) {
			throw new BusinessException("不允许操作超级管理员用户");
		}
	}

	/**
	 * 查询用户所属角色组
	 * 
	 * @param userId 用户ID
	 * @return 结果
	 */
	@Override
	public String selectUserRoleGroup(Long userId) {
		List<Role> list = roleMapper.selectRolesByUserId(userId);
		StringBuffer idsStr = new StringBuffer();
		for (Role role : list) {
			idsStr.append(role.getRoleName()).append(",");
		}
		if (StringUtils.isNotEmpty(idsStr.toString())) {
			return idsStr.substring(0, idsStr.length() - 1);
		}
		return idsStr.toString();
	}

	/**
	 * 导入用户数据
	 * 
	 * @param userList        用户数据列表
	 * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
	 * @return 结果
	 */
	@Override
	public String importUser(List<User> userList, Boolean isUpdateSupport) {
		if (StringUtils.isNull(userList) || userList.size() == 0) {
			throw new BusinessException("导入用户数据不能为空！");
		}
		int successNum = 0;
		int failureNum = 0;
		StringBuilder successMsg = new StringBuilder();
		StringBuilder failureMsg = new StringBuilder();
		String operName = ShiroUtils.getLoginName();
		String password = configService.selectConfigByKey("sys.user.initPassword");
		for (User user : userList) {
			try {
				// 验证是否存在这个用户
				User u = userMapper.selectUserByLoginName(user.getLoginName());
				if (StringUtils.isNull(u)) {
					user.setPassword(password);
					user.setCreateBy(operName);
					this.insertUser(user);
					successNum++;
					successMsg.append("<br/>" + successNum + "、账号 " + user.getLoginName() + " 导入成功");
				} else if (isUpdateSupport) {
					user.setUpdateBy(operName);
					this.updateUser(user);
					successNum++;
					successMsg.append("<br/>" + successNum + "、账号 " + user.getLoginName() + " 更新成功");
				} else {
					failureNum++;
					failureMsg.append("<br/>" + failureNum + "、账号 " + user.getLoginName() + " 已存在");
				}
			} catch (Exception e) {
				failureNum++;
				String msg = "<br/>" + failureNum + "、账号 " + user.getLoginName() + " 导入失败：";
				failureMsg.append(msg + e.getMessage());
				log.error(msg, e);
			}
		}
		if (failureNum > 0) {
			failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
			throw new BusinessException(failureMsg.toString());
		} else {
			successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
		}
		return successMsg.toString();
	}

	/**
	 * 用户状态修改
	 * 
	 * @param user 用户信息
	 * @return 结果
	 */
	@Override
	public int changeStatus(User user) {
		return userMapper.updateUser(user);
	}

	@Override
	public List<User> selectUserListByRoleId(Long roleId) {
		return userMapper.getUserByRoleId(roleId);
	}


}
