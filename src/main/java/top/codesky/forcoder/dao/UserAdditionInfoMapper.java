package top.codesky.forcoder.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import top.codesky.forcoder.model.entity.UserAdditionInfo;
import top.codesky.forcoder.model.vo.PublicationsOfMemberVo;

import java.util.List;

@Repository
@Mapper
public interface UserAdditionInfoMapper {


    /**
     * 根据一组用户id，查询对应的用户公开描述信息
     *
     * @param ids
     * @return
     */
    List<PublicationsOfMemberVo> selectMembersByIds(List<Long> ids);

    /**
     * 根据username,查询用户的介绍描述信息
     *
     * @param username
     * @return
     */
    UserAdditionInfo selectByUsername(String username);

    /**
     * 根据userId,查询用户的介绍描述信息
     *
     * @param userId
     * @return
     */
    UserAdditionInfo selectByUserId(Long userId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_addition_info
     *
     * @mbg.generated 2019-05-05 17:11:10
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_addition_info
     *
     * @mbg.generated 2019-05-05 17:11:10
     */
    int insert(UserAdditionInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_addition_info
     *
     * @mbg.generated 2019-05-05 17:11:10
     */
    int insertSelective(UserAdditionInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_addition_info
     *
     * @mbg.generated 2019-05-05 17:11:10
     */
    UserAdditionInfo selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_addition_info
     *
     * @mbg.generated 2019-05-05 17:11:10
     */
    int updateByPrimaryKeySelective(UserAdditionInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_addition_info
     *
     * @mbg.generated 2019-05-05 17:11:10
     */
    int updateByPrimaryKey(UserAdditionInfo record);
}