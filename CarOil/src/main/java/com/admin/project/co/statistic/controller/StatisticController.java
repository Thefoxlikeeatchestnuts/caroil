package com.admin.project.co.statistic.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.admin.common.utils.DateUtils;
import com.admin.common.utils.StringUtils;
import com.admin.framework.web.controller.BaseController;
import com.admin.project.co.car.domain.Car;
import com.admin.project.co.car.service.ICarService;
import com.admin.project.co.carlog.domain.CarLog;
import com.admin.project.co.carlog.service.ICarLogService;
import com.admin.project.co.statistic.domain.SearchVo;
import com.admin.project.co.statistic.service.MyDateUtil;

@Controller
@RequestMapping("/co/statistic")
public class StatisticController extends BaseController {
	private String prefix = "co/statistic";
	
	@Autowired
	private ICarService carSrv;
	@Autowired
	private ICarLogService carLogSrv;
	
	@GetMapping("/all")
	public String carlog(ModelMap mmp) {
		Date now =  DateUtils.getNowDate();
		Date begin = MyDateUtil.addYears(now, -1);
		Map<String,Object> param = new HashMap<>();
		param.put("beginCreateTime", DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, begin));
		param.put("endCreateTime",  DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, now));
		CarLog carLog = new CarLog();
		carLog.setParams(param);
		List<CarLog> selectCarLogList = carLogSrv.selectCarLogList(carLog);
		
		Map<String,Double> all = new HashMap<>();
		for (CarLog carLog2 : selectCarLogList) {
			Double sum = carLog2.getSum();
			String num = carLog2.getCar().getNum();
			if(all.containsKey(num)) {
				sum+=all.get(num);
			}
			all.put(num, sum);
		}
		List<String> keys = new ArrayList<>();
		List<Double> values =  new ArrayList<>();
		for(String key : all.keySet()) {
			keys.add(key);
			values.add(all.get(key));
		}
		
		List<Car> cars = carSrv.selectCarList(new Car());
		mmp.put("cars", cars);
		mmp.put("keys", keys);
		mmp.put("values", values);
		mmp.put("data", all);
		return prefix + "/carlog";
	}
	@PostMapping("/searchAll")
	@ResponseBody
	public Map<String,Double> searchAll(@RequestBody SearchVo vo) {
		Map<String,Object> param = new HashMap<>();
		if(StringUtils.isNotEmpty(vo.getStartTime())) {
			param.put("beginCreateTime", vo.getStartTime());
		}
		if(StringUtils.isNotEmpty(vo.getEndTime())) {
			param.put("endCreateTime", vo.getEndTime());
		}
		
		CarLog carLog = new CarLog();
		carLog.setParams(param);
		carLog.setCars(vo.getCars());
		List<CarLog> selectCarLogList = carLogSrv.selectCarLogList(carLog);
		
		Map<String,Double> all = new HashMap<>();
		for (CarLog carLog2 : selectCarLogList) {
			Double sum = carLog2.getSum();
			String num = carLog2.getCar().getNum();
			if(all.containsKey(num)) {
				sum+=all.get(num);
			}
			all.put(num, sum);
		}
		return all;
	}
	
	@GetMapping("/one")
	public String one(ModelMap mmp) {
		
		List<Car> cars = carSrv.selectCarList(new Car());
		mmp.put("cars", cars);
		return prefix + "/one";
	}
	@PostMapping("/searchOne")
	@ResponseBody
	public Map<String,List<String>> searchOne(@RequestBody SearchVo vo) {
		Map<String,Object> param = new HashMap<>();
		if(StringUtils.isNotEmpty(vo.getStartTime())) {
			param.put("beginCreateTime", vo.getStartTime());
		}
		if(StringUtils.isNotEmpty(vo.getEndTime())) {
			param.put("endCreateTime", vo.getEndTime());
		}
		
		CarLog carLog = new CarLog();
		carLog.setParams(param);
		carLog.setCarId(vo.getCarId());
		List<CarLog> selectCarLogList = carLogSrv.selectCarLogList(carLog);
		
		List<String> names = new ArrayList<String>();
		List<String> values = new ArrayList<String>();
		for (CarLog carLog2 : selectCarLogList) {
			names.add(DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS, carLog2.getCreateTime()));
			values.add( carLog2.getSum().toString());
		}
		Map<String,List<String>> all = new HashMap<>();
		all.put("names", names);
		all.put("values", values);
		return all;
	}
}
