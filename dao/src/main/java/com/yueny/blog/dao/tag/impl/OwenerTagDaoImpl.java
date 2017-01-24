package com.yueny.blog.dao.tag.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yueny.blog.dao.tag.IOwenerTagDao;
import com.yueny.blog.dao.tag.IOwenerTagMapper;
import com.yueny.blog.entry.tag.OwenerTagEntry;
import com.yueny.kapo.api.annnotation.DbSchemaType;
import com.yueny.kapo.api.annnotation.TableSeg;
import com.yueny.kapo.core.condition.builder.QueryBuilder;
import com.yueny.kapo.core.condition.column.operand.enums.BasicSqlOperand;
import com.yueny.kapo.core.dao.SingleTableDao;
import com.yueny.rapid.lang.util.enums.EnableType;

/**
 * 个人分类类目持久层操作
 *
 * @author 袁洋 2015年8月24日 下午1:46:10
 *
 */
@DbSchemaType("BLOG")
@TableSeg(tableName = "OWENER_TAG")
@Repository
public class OwenerTagDaoImpl extends SingleTableDao<OwenerTagEntry> implements IOwenerTagDao {
	@Autowired
	private IOwenerTagMapper owenerTagMapper;

	/*
	 * (non-Javadoc)
	 *
	 * @see com.yueny.blog.dao.tag.IOwenerTagDao#plusCorrelaArticle(long, int)
	 */
	@Override
	public boolean plusCorrelaArticle(final long primaryId, final int step) {
		return owenerTagMapper.plusCorrelaArticle(primaryId, step) == 1;
		//
		// final UpdateBuilder builder =
		// UpdateBuilder.builder().set("CORRELA_ARTICLE_SUM",
		// "CORRELA_ARTICLE_SUM + 1")
		// .where("OWENER_TAG_ID", primaryId).build();
		//
		// return super.updateByColumns(builder) > 0;
	}

	@Override
	public List<OwenerTagEntry> queryAllByUid(final String uid) {
		// WEIGHT '权重, 1-255'
		final QueryBuilder builder = QueryBuilder.builder().where("UID", uid).build();

		return super.queryListByColumns(builder);
	}

	@Override
	public List<OwenerTagEntry> queryByCategoriesTagCode(final String categoriesTagCode) {
		final QueryBuilder builder = QueryBuilder.builder().where("CATEGORIES_TAG_CODE", categoriesTagCode).build();

		return super.queryListByColumns(builder);
	}

	@Override
	public List<OwenerTagEntry> queryByID(final Set<Long> entryIds) {
		final QueryBuilder builder = QueryBuilder.builder().where("OWENER_TAG_ID", BasicSqlOperand.IN, entryIds)
				.build();
		return super.queryListByColumns(builder);
	}

	@Override
	public List<OwenerTagEntry> queryByUid(final String uid) {
		final QueryBuilder builder = QueryBuilder.builder().where("UID", uid)
				.where("IS_SHOW", EnableType.ENABLE.getValue()).build();

		return super.queryListByColumns(builder);
	}

}
