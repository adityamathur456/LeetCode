class DetectSquares {
    HashMap<Integer, HashMap<Integer, Integer>> map;

    public DetectSquares() {
        map = new HashMap<>();
    }
    
    public void add(int[] point) {
        int x = point[0];
        int y = point[1];

        map.putIfAbsent(x, new HashMap<>());
        map.get(x).put(y, map.get(x).getOrDefault(y, 0)+1);
    }
    
    public int count(int[] point) {
        int x1 = point[0];
        int y1 = point[1];

        if(!map.containsKey(x1)) return 0;

        HashMap<Integer, Integer> sameX = map.get(x1);

        int res = 0;
        for(int y2 : sameX.keySet()){
            if(y2 == y1){
                continue;
            }

            int d = Math.abs(y2-y1);

            // right square
            res += sameX.get(y2) * map.getOrDefault(x1+d, new HashMap<>()).getOrDefault(y1, 0)
            * map.getOrDefault(x1+d, new HashMap<>()).getOrDefault(y2, 0);

            // left square
            res += sameX.get(y2) * map.getOrDefault(x1-d, new HashMap<>()).getOrDefault(y1, 0)
            * map.getOrDefault(x1-d, new HashMap<>()).getOrDefault(y2, 0);
        }
        return res;
    }
}

/**
 * Your DetectSquares object will be instantiated and called as such:
 * DetectSquares obj = new DetectSquares();
 * obj.add(point);
 * int param_2 = obj.count(point);
 */