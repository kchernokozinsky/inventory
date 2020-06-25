package services;

import DTO.GoodsDTO;
import entity.Goods;
import org.hibernate.HibernateException;

import javax.persistence.EntityExistsException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class GoodsService {

    private static GoodsService goodsService;

    public static GoodsService getGoodsService(){
        if (goodsService == null){
            goodsService = new GoodsService();
        }
        return goodsService;
    }

    private GoodsService() {}

    public GoodsDTO save(GoodsDTO goodsDTO) {
        Goods goods = new Goods(goodsDTO);
        try {
            getByName(goods.getName());
            throw new EntityExistsException("Goods with this name alreaty exict");
        }catch (NoSuchElementException e){
            try {
                GoodsDao.getGoodsDao().save(goods);
            }catch (HibernateException ex){
                ex.printStackTrace();
            }
            return new GoodsDTO(goods.getId(), goods.getName(), goods.getNumber(), goods.getGroupId());
        }
    }

    public void delete(GoodsDTO goodsDTO) {
        Goods goods = GoodsDao.getGoodsDao().findById(goodsDTO.getId());
        GoodsDao.getGoodsDao().delete(goods);
    }

    public void update(GoodsDTO goodsDTO) {
        GoodsDao.getGoodsDao().findById(goodsDTO.getId());
        Goods goods = new Goods(goodsDTO);
        GoodsDao.getGoodsDao().update(goods);
    }

    public List<GoodsDTO> getAll() {
        List<Goods> goods;
        List<GoodsDTO> goodsDTO = new ArrayList<GoodsDTO>();
        goods = GoodsDao.getGoodsDao().findAll();
        for (Goods g: goods){
            goodsDTO.add(new GoodsDTO(g.getId(), g.getName(), g.getNumber(), g.getGroupId()));
        }
        return goodsDTO;
    }

    public GoodsDTO getById(int id) {
        Goods goods  = GoodsDao.getGoodsDao().findById(id);
        return new GoodsDTO(goods.getId(), goods.getName(), goods.getNumber(), goods.getGroupId());
    }

    public GoodsDTO getByName(String name) {
        Goods goods = GoodsDao.getGoodsDao().findByName(name);
        return new GoodsDTO(goods.getId(), goods.getName(), goods.getNumber(), goods.getGroupId());
    }

    public List<GoodsDTO> getByNameLike(String name) {
        if("".equals(name)){
            return getAll();
        }
        List<Goods> goods;
        List<GoodsDTO> goodsDTO = new ArrayList<GoodsDTO>();
        goods = GoodsDao.getGoodsDao().findByNameLike(name);
        for (Goods g: goods){
            goodsDTO.add(new GoodsDTO(g.getId(), g.getName(), g.getNumber(), g.getGroupId()));
        }
        return goodsDTO;
    }

    public List<GoodsDTO> getListByNumber(int number) {
        List<Goods> goods;
        List<GoodsDTO> goodsDTO = new ArrayList<GoodsDTO>();
        goods = GoodsDao.getGoodsDao().getListByNumber(number);
        for (Goods g: goods){
            goodsDTO.add(new GoodsDTO(g.getId(), g.getName(), g.getNumber(), g.getGroupId()));
        }
        return goodsDTO;
    }

    public List<GoodsDTO> getListByGroupId(int id) {
        List<Goods> goods;
        List<GoodsDTO> goodsDTO = new ArrayList<GoodsDTO>();
        goods = GoodsDao.getGoodsDao().getListByGroupId(id);
        for (Goods g: goods){
            goodsDTO.add(new GoodsDTO(g.getId(), g.getName(), g.getNumber(), g.getGroupId()));
        }
        return goodsDTO;
    }

    public GoodsDTO plusNumber(GoodsDTO goodsDTO, int number){
        if (number <= 0 || goodsDTO.getNumber() < number){
            throw new IllegalArgumentException("number must be > 0");
        }
        return pmNumber(goodsDTO, number);
    }

    public GoodsDTO minusNumber(GoodsDTO goodsDTO, int number){
        if (number <= 0 || goodsDTO.getNumber() < number){
            throw new IllegalArgumentException("number must be > 0");
        }
        return pmNumber(goodsDTO, -number);
    }

    private GoodsDTO pmNumber(GoodsDTO goodsDTO, int number){
        Goods goods = GoodsDao.getGoodsDao().findById(goodsDTO.getId());
        goods.setNumber(goods.getNumber() + number);
        GoodsDao.getGoodsDao().update(goods);
        return new GoodsDTO(goods.getId(), goods.getName(), goods.getNumber(), goods.getGroupId());
    }

    public GoodsDTO changeGroupId(GoodsDTO goodsDTO, int group_id){
        Goods goods = GoodsDao.getGoodsDao().findById(goodsDTO.getId());
        goods.setGroupId(group_id);
        GoodsDao.getGoodsDao().update(goods);
        return new GoodsDTO(goods.getId(), goods.getName(), goods.getNumber(), goods.getGroupId());
    }

    public GoodsDTO changeName(GoodsDTO goodsDTO, String name){
        Goods goods = GoodsDao.getGoodsDao().findById(goodsDTO.getId());
        goods.setName(name);
        GoodsDao.getGoodsDao().update(goods);
        return new GoodsDTO(goods.getId(), goods.getName(), goods.getNumber(), goods.getGroupId());
    }

    public GoodsDTO changeNumber(GoodsDTO goodsDTO, int number){
        if (number < 0){
            throw new IllegalArgumentException("number must be >= 0");
        }
        Goods goods = GoodsDao.getGoodsDao().findById(goodsDTO.getId());
        goods.setNumber(number);
        GoodsDao.getGoodsDao().update(goods);
        return new GoodsDTO(goods.getId(), goods.getName(), goods.getNumber(), goods.getGroupId());
    }

}