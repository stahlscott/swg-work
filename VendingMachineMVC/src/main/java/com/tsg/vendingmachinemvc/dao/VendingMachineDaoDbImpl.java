/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.vendingmachinemvc.dao;

import com.tsg.vendingmachinemvc.dto.Item;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Scott Stahl <stahl.scott@gmail.com>
 */
public class VendingMachineDaoDbImpl implements VendingMachineDao {

    private static final String SQL_INSERT_ITEM
            = "insert into item (position, name, cost, quantity, img_url) values (?, ?, ?, ?, ?)";

    private static final String SQL_UPDATE_ITEM
            = "update item set name = ?, cost = ?, quantity = ?, img_url = ? where position = ?";

    private static final String SQL_SELECT_ITEM
            = "select * from item where position = ?";

    private static final String SQL_SELECT_ALL_ITEMS
            = "select * from item";

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addItem(Item item) {
        if (getItemByPosition(item.getPosition()) != null) {
            jdbcTemplate.update(SQL_UPDATE_ITEM, item.getName(), item.getCost(), item.getQuantity(),
                    item.getImgUrl(), item.getPosition());
        } else {
            jdbcTemplate.update(SQL_INSERT_ITEM, item.getPosition(), item.getName(), item.getCost(),
                    item.getQuantity(), item.getImgUrl());
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Item vendItem(String position) {
        Item item = getItemByPosition(position);
        item.setQuantity(item.getQuantity() - 1);
        addItem(item);
        return item;
    }

    @Override
    public Item getItemByPosition(String position) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_ITEM, new ItemMapper(), position);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Item> getAllItems() {
        return jdbcTemplate.query(SQL_SELECT_ALL_ITEMS, new ItemMapper());
    }

    @Override
    public void loadItemList() {
        // unnecessary function with database implementation
    }

    private static class ItemMapper implements RowMapper<Item> {

        @Override
        public Item mapRow(ResultSet rs, int i) throws SQLException {
            Item item = new Item();

            item.setPosition(rs.getString("position"));
            item.setName(rs.getString("name"));
            item.setCost(rs.getDouble("cost"));
            item.setQuantity(rs.getInt("quantity"));
            item.setImgUrl(rs.getString("img_url"));

            return item;
        }

    }

}
