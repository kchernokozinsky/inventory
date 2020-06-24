package services;

import DTO.GoodsDTO;
import entity.Goods;

import java.util.List;

public class GoodsService {

    private static GoodsService goodsService;

    public static GoodsService getGoodsService(){
        if (goodsService == null){
            goodsService = new GoodsService();
        }
        return goodsService;
    }

    private GoodsService() {}

    public void save(GoodsDTO goods) {
//        GoodsDao.getGroupDao().save(goods);
    }

    public void delete(GoodsDTO goods) {
//        GoodsDao.getGroupDao().delete(goods);
    }

    public void update(GoodsDTO goods) {
//        GoodsDao.getGroupDao().update(goods);
    }

    public List<GoodsDTO> findAll() {
//        return GoodsDao.getGroupDao().findAll();
        return null;
    }

    public GoodsDTO findById(int id) {
//        return GoodsDao.getGroupDao().findGoodsById(id);
        return null;
    }

    public GoodsDTO findByName(String name) {
//        return GoodsDao.getGroupDao().findGoodsByName(name);
        return null;
    }

    public List<GoodsDTO> findByNameLike(String name) {
//        return GoodsDao.getGroupDao().findGoodsByName(name);
        return null;
    }

    public List<GoodsDTO> listByNumber(int number) {
//        return GoodsDao.getGroupDao().listByNumber(number);
        return null;
    }


}