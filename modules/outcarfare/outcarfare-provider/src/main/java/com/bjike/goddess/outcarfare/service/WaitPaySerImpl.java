package com.bjike.goddess.outcarfare.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.dispatchcar.api.DispatchCarInfoAPI;
import com.bjike.goddess.dispatchcar.bo.DispatchCarInfoBO;
import com.bjike.goddess.dispatchcar.entity.DispatchCarInfo;
import com.bjike.goddess.outcarfare.bo.*;
import com.bjike.goddess.outcarfare.dto.WaitPayDTO;
import com.bjike.goddess.outcarfare.entity.WaitPay;
import com.bjike.goddess.outcarfare.enums.GuideAddrStatus;
import com.bjike.goddess.outcarfare.to.GuidePermissionTO;
import com.bjike.goddess.outcarfare.to.WaitPayTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 等待付款业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-05 03:11 ]
 * @Description: [ 等待付款业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "outcarfareSerCache")
@Service
public class WaitPaySerImpl extends ServiceImpl<WaitPay, WaitPayDTO> implements WaitPaySer {
    @Autowired
    private DispatchCarInfoAPI dispatchCarInfoAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private UserAPI userAPI;

    /**
     * 核对查看权限（部门级别）
     */
    private void checkSeeIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("1");
            if (!flag) {
                throw new SerException("您不是相应部门的人员，不可以操作");
            }
        }
        RpcTransmit.transmitUserToken(userToken);
    }

    /**
     * 核对添加修改删除审核权限（岗位级别）
     */
    private void checkAddIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("2");
            if (!flag) {
                throw new SerException("您不是相应部门的人员，不可以操作");
            }
        }
        RpcTransmit.transmitUserToken(userToken);
    }

    /**
     * 核对查看权限（部门级别）
     */
    private Boolean guideSeeIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("1");
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 核对添加修改删除审核权限（岗位级别）
     */
    private Boolean guideAddIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("2");
        } else {
            flag = true;
        }
        return flag;
    }

    @Override
    public Boolean sonPermission() throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSee = guideSeeIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAdd = guideAddIdentity();
        if (flagSee || flagAdd) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        GuideAddrStatus guideAddrStatus = guidePermissionTO.getGuideAddrStatus();
        Boolean flag = true;
        switch (guideAddrStatus) {
            case LIST:
                flag = guideSeeIdentity();
                break;
            case ADD:
                flag = guideAddIdentity();
                break;
            case EDIT:
                flag = guideAddIdentity();
                break;
            case AUDIT:
                flag = guideAddIdentity();
                break;
            case DELETE:
                flag = guideAddIdentity();
                break;
            case COLLECT:
                flag = guideAddIdentity();
                break;
            case SEE:
                flag = guideSeeIdentity();
                break;
            case pay:
                flag = guideAddIdentity();
                break;
            default:
                flag = true;
                break;
        }

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }

    @Override
    public WaitPayBO save(WaitPayTO to) throws SerException {
        checkAddIdentity();
        WaitPay waitPay = BeanTransform.copyProperties(to, WaitPay.class, true);
        super.save(waitPay);
        return BeanTransform.copyProperties(waitPay, WaitPayBO.class);
    }

    @Override
    public void pay(WaitPayTO to) throws SerException {
        checkAddIdentity();
        WaitPay waitPay = super.findById(to.getId());
        waitPay.setIsPay(to.getIsPay());
        super.update(waitPay);
    }

    @Override
    public void delete(String id) throws SerException {

    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public List<WaitPayBO> list(WaitPayDTO dto) throws SerException {
        checkSeeIdentity();
        String userToken=RpcTransmit.getUserToken();
        List<DispatchCarInfoBO> list = dispatchCarInfoAPI.allWaitPay();
        List<WaitPay> waitPays = super.findAll();
        if (list != null) {
            for (DispatchCarInfoBO v : list) {
                if (waitPays.size() == 0) {
                    WaitPay waitPay = new WaitPay();
                    BeanUtils.copyProperties(v, waitPay);
                    waitPay.setDriverName(v.getDriver());
                    waitPay.setCarDate(v.getDispatchDate());
                    waitPay.setNumber(v.getNumber());
                    waitPay.setArrival(v.getArea());
                    waitPay.setCarPrice(v.getCarRentalCost());
                    waitPay.setAcctype(v.getAcctype());
                    waitPay.setOvertimeHour((double) v.getOverWorkTime());
                    waitPay.setOvertimeFee(v.getOverWorkCost());
                    waitPay.setAllowance(v.getMealCost());
                    waitPay.setOvertimePrice(v.getCarRentalCost() / 8);
                    waitPay.setParkFee(v.getParkCost() + v.getRoadCost());
                    waitPay.setAmount(v.getCost());
                    waitPay.setDispatchCarInfoId(v.getId());
                    super.save(waitPay);
                } else {
                    boolean b1 = true;
                    for (WaitPay p : waitPays) {
                        if (p.getDispatchCarInfoId().equals(v.getId())) {
                            LocalDateTime a = p.getCreateTime();
                            LocalDateTime b = p.getModifyTime();
                            String id = p.getId();
                            BeanUtils.copyProperties(v, p);
                            p.setId(id);
                            p.setCreateTime(a);
                            p.setModifyTime(b);
                            p.setDriverName(v.getDriver());
                            p.setCarDate(v.getDispatchDate());
                            p.setNumber(v.getNumber());
                            p.setArrival(v.getArea());
                            p.setAcctype(v.getAcctype());
                            p.setCarPrice(v.getCarRentalCost());
                            p.setOvertimeHour((double) v.getOverWorkTime());
                            p.setOvertimeFee(v.getOverWorkCost());
                            p.setAllowance(v.getMealCost());
                            p.setParkFee(v.getParkCost() + v.getRoadCost());
                            p.setAmount(v.getCost());
                            p.setOvertimePrice(v.getCarRentalCost() / 8);
                            p.setDispatchCarInfoId(v.getId());
                            p.setDispatchCarInfoId(v.getId());
                            super.update(p);
                            b1 = false;
                        }
                    }
                    if (b1) {
                        WaitPay waitPay = new WaitPay();
                        BeanUtils.copyProperties(v, waitPay);
                        waitPay.setDispatchCarInfoId(v.getId());
                        waitPay.setDriverName(v.getDriver());
                        waitPay.setCarDate(v.getDispatchDate());
                        waitPay.setNumber(v.getNumber());
                        waitPay.setArrival(v.getArea());
                        waitPay.setAcctype(v.getAcctype());
                        waitPay.setCarPrice(v.getCarRentalCost());
                        waitPay.setOvertimeHour((double) v.getOverWorkTime());
                        waitPay.setOvertimeFee(v.getOverWorkCost());
                        waitPay.setAllowance(v.getMealCost());
                        waitPay.setParkFee(v.getParkCost() + v.getRoadCost());
                        waitPay.setAmount(v.getCost());
                        waitPay.setOvertimePrice(v.getCarRentalCost() / 8);
                        waitPay.setDispatchCarInfoId(v.getId());
                        super.save(waitPay);
                    }
                }
            }
        }
        for (WaitPay p : super.findAll()) {
            DispatchCarInfoBO v = dispatchCarInfoAPI.findById(p.getDispatchCarInfoId());
            if (v == null || v.getPay()) {
                super.remove(p.getId());
            }
        }
        dto.getConditions().add(Restrict.eq("isPay", Boolean.TRUE));
        List<WaitPay> l = super.findByCis(dto, true);
        RpcTransmit.transmitUserToken(userToken);
        return BeanTransform.copyProperties(l, WaitPayBO.class);
    }

    @Override
    public WaitPayBO findByID(String id) throws SerException {
        WaitPay waitPay = super.findById(id);
        return BeanTransform.copyProperties(waitPay, WaitPayBO.class);
    }

    @Override
    public List<DriverCountBO> driverCount() throws SerException {
        WaitPayDTO dto = new WaitPayDTO();
        list(dto);
        List<PayBO> list = findAlreadyPays();
        Set<String> drivers = findAllDrivers();
        Set<Double> prices = findAllPrices();
        List<DriverCountBO> boList = new ArrayList<DriverCountBO>();
        Double overtimeFeeSum = 0.00;
        Double allowanceSum = 0.00;
        Double parkFeeSum = 0.00;
        Double amountSum = 0.00;
        for (String driver : drivers) {
            for (Double price : prices) {
                for (PayBO w : list) {
                    if (w.getDriverName().equals(driver) && w.getCarPrice().equals(price)) {
                        overtimeFeeSum += w.getOvertimeFee();
                        allowanceSum += w.getAllowance();
                        parkFeeSum += w.getParkFee();
                        amountSum += w.getAmount();
                    }
                }
                if (amountSum != 0) {
                    DriverCountBO bo = new DriverCountBO();
                    bo.setDriverName(driver);
                    bo.setCarPrice(price);
                    bo.setOvertimeFeeSum(overtimeFeeSum);
                    bo.setAllowanceSum(allowanceSum);
                    bo.setParkFeeSum(parkFeeSum);
                    bo.setAmountSum(amountSum);
                    boList.add(bo);
                    overtimeFeeSum = 0.00;
                    allowanceSum = 0.00;
                    parkFeeSum = 0.00;
                    amountSum = 0.00;      //置为0
                }
            }
        }
        return boList;
    }

    @Override
    public List<ArrivalCountBO> arrivalCount() throws SerException {
        WaitPayDTO dto = new WaitPayDTO();
        list(dto);
        List<PayBO> list = findAlreadyPays();
        Set<String> arrivals = findAllArrivals();
        Set<Double> prices = findAllPrices();
        List<ArrivalCountBO> boList = new ArrayList<ArrivalCountBO>();
        Double overtimeFeeSum = 0.00;
        Double allowanceSum = 0.00;
        Double parkFeeSum = 0.00;
        Double amountSum = 0.00;
        for (String arrival : arrivals) {
            for (Double price : prices) {
                for (PayBO w : list) {
                    if (w.getArrival().equals(arrival) && w.getCarPrice().equals(price)) {
                        overtimeFeeSum += w.getOvertimeFee();
                        allowanceSum += w.getAllowance();
                        parkFeeSum += w.getParkFee();
                        amountSum += w.getAmount();
                    }
                }
                if (amountSum != 0) {
                    ArrivalCountBO bo = new ArrivalCountBO();
                    bo.setArrival(arrival);
                    bo.setCarPrice(price);
                    bo.setOvertimeFeeSum(overtimeFeeSum);
                    bo.setAllowanceSum(allowanceSum);
                    bo.setParkFeeSum(parkFeeSum);
                    bo.setAmountSum(amountSum);
                    boList.add(bo);
                    overtimeFeeSum = 0.00;
                    allowanceSum = 0.00;
                    parkFeeSum = 0.00;
                    amountSum = 0.00;      //置为0
                }
            }
        }
        return boList;
    }

    @Override
    public List<CarUserCountBO> carUserCount() throws SerException {
        WaitPayDTO dto = new WaitPayDTO();
        list(dto);
        List<PayBO> list = findAlreadyPays();
        Set<String> carUsers = findAllCarUsers();
        Set<Double> prices = findAllPrices();
        List<CarUserCountBO> boList = new ArrayList<CarUserCountBO>();
        Double overtimeFeeSum = 0.00;
        Double allowanceSum = 0.00;
        Double parkFeeSum = 0.00;
        Double amountSum = 0.00;
        for (String carUser : carUsers) {
            for (Double price : prices) {
                for (PayBO w : list) {
                    if (w.getCarUser().equals(carUser) && w.getCarPrice().equals(price)) {
                        overtimeFeeSum += w.getOvertimeFee();
                        allowanceSum += w.getAllowance();
                        parkFeeSum += w.getParkFee();
                        amountSum += w.getAmount();
                    }
                }
                if (amountSum != 0) {
                    CarUserCountBO bo = new CarUserCountBO();
                    bo.setCarUser(carUser);
                    bo.setCarPrice(price);
                    bo.setOvertimeFeeSum(overtimeFeeSum);
                    bo.setAllowanceSum(allowanceSum);
                    bo.setParkFeeSum(parkFeeSum);
                    bo.setAmountSum(amountSum);
                    boList.add(bo);
                    overtimeFeeSum = 0.00;
                    allowanceSum = 0.00;
                    parkFeeSum = 0.00;
                    amountSum = 0.00;      //置为0
                }
            }
        }
        return boList;
    }

    @Override
    public List<WaitPayBO> pays(WaitPayDTO dto) throws SerException {
        checkSeeIdentity();
        dto.getConditions().add(Restrict.eq("isPay", Boolean.FALSE));
        List<WaitPay> l = super.findByCis(dto, true);
        return BeanTransform.copyProperties(l, WaitPayBO.class);
    }

    @Override
    public Long waitCountSum(WaitPayDTO dto) throws SerException {
        dto.getConditions().add(Restrict.eq("isPay", Boolean.TRUE));
        return super.count(dto);
    }

    @Override
    public Long payCountSum(WaitPayDTO dto) throws SerException {
        dto.getConditions().add(Restrict.eq("isPay", Boolean.FALSE));
        return super.count(dto);
    }

    private List<PayBO> findAlreadyPays() throws SerException {
        list(new WaitPayDTO());
        List<WaitPay> list = super.findAll();
        List<PayBO> boList = new ArrayList<PayBO>();
        for (WaitPay w : list) {
            if (w.getIsPay()) {
                PayBO bo = new PayBO();
                BeanUtils.copyProperties(w, bo);
                bo.setHavePay(true);
                boList.add(bo);
            }
        }
        return boList;
    }

    /**
     * 查找所有司机
     *
     * @return class String
     * @throws SerException
     */
    private Set<String> findAllDrivers() throws SerException {
        list(new WaitPayDTO());
        List<WaitPay> list = super.findAll();
        Set<String> set = new HashSet<String>();
        for (WaitPay w : list) {
            set.add(w.getDriverName());
        }
        return set;
    }

    /**
     * 查找所有地区
     *
     * @return class String
     * @throws SerException
     */
    private Set<String> findAllArrivals() throws SerException {
        list(new WaitPayDTO());
        List<WaitPay> list = super.findAll();
        Set<String> set = new HashSet<String>();
        for (WaitPay w : list) {
            set.add(w.getArrival());
        }
        return set;
    }

    /**
     * 查找所有用车人
     *
     * @return class String
     * @throws SerException
     */
    private Set<String> findAllCarUsers() throws SerException {
        list(new WaitPayDTO());
        List<WaitPay> list = super.findAll();
        Set<String> set = new HashSet<String>();
        for (WaitPay w : list) {
            set.add(w.getCarUser());
        }
        return set;
    }

    /**
     * 查找所有租车单价
     *
     * @return class Double
     * @throws SerException
     */
    private Set<Double> findAllPrices() throws SerException {
        list(new WaitPayDTO());
        List<WaitPay> list = super.findAll();
        Set<Double> set = new HashSet<Double>();
        for (WaitPay w : list) {
            set.add(w.getCarPrice());
        }
        return set;
    }
}