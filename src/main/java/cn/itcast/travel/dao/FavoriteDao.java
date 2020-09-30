package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Favorite;

public interface FavoriteDao {
    public Favorite findByRidAndUid(int rid, int uid);
    public int findFavoriteCountsByRid(int rid);
//试试SVN
    public void addFavorite(int rid, int uid);
}
