package top.codesky.forcoder.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.codesky.forcoder.model.entity.Question;
import top.codesky.forcoder.model.entity.QuestionWithAuthor;
import top.codesky.forcoder.model.query.QuestionDeleteParams;

import java.util.List;

@Mapper
@Repository
public interface QuestionMapper {

    /**
     * 分页查询
     * todo: 应该实现feed流获取，这只是个demo
     *
     * @param offset
     * @param limit
     * @return
     */
    List<QuestionWithAuthor> selectLatestQuestionByPage(@Param("offset") long offset, @Param("limit") long limit);

    /**
     * 用户删除自己的问题，即
     * 根据questionId和authorId删除问题
     *
     * @param questionDeleteParams 封装questionId和authorId
     * @return 0 or affected rows
     */
    int deleteByQuestionIdAndUserId(QuestionDeleteParams questionDeleteParams);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table question
     *
     * @mbg.generated 2019-04-22 20:06:12
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table question
     *
     * @mbg.generated 2019-04-22 20:06:12
     */
    int insert(Question record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table question
     *
     * @mbg.generated 2019-04-22 20:06:12
     */
    int insertSelective(Question record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table question
     *
     * @mbg.generated 2019-04-22 20:06:12
     */
    Question selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table question
     *
     * @mbg.generated 2019-04-22 20:06:12
     */
    int updateByPrimaryKeySelective(Question record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table question
     *
     * @mbg.generated 2019-04-22 20:06:12
     */
    int updateByPrimaryKeyWithBLOBs(Question record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table question
     *
     * @mbg.generated 2019-04-22 20:06:12
     */
    int updateByPrimaryKey(Question record);
}