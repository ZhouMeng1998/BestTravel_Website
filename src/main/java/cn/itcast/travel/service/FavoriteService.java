package cn.itcast.travel.service;

public interface FavoriteService {
    public boolean isFavorite(String rid, int uid);
    public void addFavorite(String rid, int uid);
}
