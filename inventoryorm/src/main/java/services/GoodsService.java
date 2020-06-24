package services;

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

    public void save(Goods goods) {
        GoodsDao.getGroupDao().save(goods);
    }

    public void delete(Goods goods) {
        GoodsDao.getGroupDao().delete(goods);
    }

    public void update(Goods goods) {
        GoodsDao.getGroupDao().update(goods);
    }

    public List<Goods> findAll() {
        return GoodsDao.getGroupDao().findAll();
    }

    public Goods findById(int id) {
        return GoodsDao.getGroupDao().findGoodsById(id);
    }

    public Goods findByName(String name) {
        return GoodsDao.getGroupDao().findGoodsByName(name);
    }

    public List<Goods> findByNameLike(String name) {
//        return GoodsDao.getGroupDao().findGoodsByName(name);
        return null;
    }

    public List<Goods> listByNumber(int number) {
        return GoodsDao.getGroupDao().listByNumber(number);
    }


}