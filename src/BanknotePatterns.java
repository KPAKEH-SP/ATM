public enum BanknotePatterns {
    _5000(5000), _2000(2000), _1000(1000), _500(500), _200(200), _100(100), _50(50);
    private int banknote;

    BanknotePatterns(int banknote) {
        this.banknote = banknote;
    }

    public int getBanknote() {
        return banknote;
    }
}
