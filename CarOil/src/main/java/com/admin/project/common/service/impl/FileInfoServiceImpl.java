package com.admin.project.common.service.impl;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admin.common.constant.Constants;
import com.admin.common.utils.StringUtils;
import com.admin.common.utils.text.Convert;
import com.admin.framework.config.RuoYiConfig;
import com.admin.project.common.domain.FileInfo;
import com.admin.project.common.mapper.FileInfoMapper;
import com.admin.project.common.service.IFileInfoService;

/**
 * 文件信息Service业务层处理
 * 
 * 
 * @date 2020-01-26
 */
@Service
public class FileInfoServiceImpl implements IFileInfoService {
	@Autowired
	private FileInfoMapper fileInfoMapper;

	/**
	 * 查询文件信息
	 * 
	 * @param fileId 文件信息ID
	 * @return 文件信息
	 */
	@Override
	public FileInfo selectFileInfoById(Long fileId) {
		return fileInfoMapper.selectFileInfoById(fileId);
	}

	/**
	 * 查询文件信息列表
	 * 
	 * @param fileInfo 文件信息
	 * @return 文件信息
	 */
	@Override
	public List<FileInfo> selectFileInfoList(FileInfo fileInfo) {
		return fileInfoMapper.selectFileInfoList(fileInfo);
	}

	/**
	 * 新增文件信息
	 * 
	 * @param fileInfo 文件信息
	 * @return 结果
	 */
	@Override
	public int insertFileInfo(FileInfo fileInfo) {
		return fileInfoMapper.insertFileInfo(fileInfo);
	}

	/**
	 * 修改文件信息
	 * 
	 * @param fileInfo 文件信息
	 * @return 结果
	 */
	@Override
	public int updateFileInfo(FileInfo fileInfo) {
		return fileInfoMapper.updateFileInfo(fileInfo);
	}

	/**
	 * 删除文件信息对象
	 * 
	 * @param ids 需要删除的数据ID
	 * @return 结果
	 */
	@Override
	public int deleteFileInfoByIds(String ids) {
		String[] strArray = Convert.toStrArray(ids);
		for (String id : strArray) {
			FileInfo selectFileInfoById = fileInfoMapper.selectFileInfoById(Long.parseLong(id));
			String filePath = selectFileInfoById.getFilePath();
			// 本地资源路径
			String localPath = RuoYiConfig.getProfile();
			// 数据库资源地址
			String downloadPath = localPath + StringUtils.substringAfter(filePath, Constants.RESOURCE_PREFIX);
			System.out.println(downloadPath);
			File file = new File(downloadPath);
			if (!file.isDirectory()) {
				file.delete();
			}
		}
		return fileInfoMapper.deleteFileInfoByIds(Convert.toStrArray(ids));
	}

	/**
	 * 删除文件信息信息
	 * 
	 * @param fileId 文件信息ID
	 * @return 结果
	 */
	@Override
	public int deleteFileInfoById(Long fileId) {
		FileInfo selectFileInfoById = fileInfoMapper.selectFileInfoById(fileId);
		String filePath = selectFileInfoById.getFilePath();
		deleteDiskFile(filePath);
		return fileInfoMapper.deleteFileInfoById(fileId);
	}

	private void deleteDiskFile(String filePath) {
		// 本地资源路径
		String localPath = RuoYiConfig.getProfile();
		// 数据库资源地址
		String downloadPath = localPath + StringUtils.substringAfter(filePath, Constants.RESOURCE_PREFIX);
		File file = new File(downloadPath);
		if (!file.isDirectory()) {
			file.delete();
		}
	}
}
