    package com.mikejuliet.userservice.entites.userEntity.otherUserEntities;

    import jakarta.persistence.Entity;
    import jakarta.persistence.Id;
    import jakarta.persistence.Table;
    import lombok.AllArgsConstructor;
    import lombok.Getter;
    import lombok.NoArgsConstructor;
    import lombok.Setter;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Entity
    @Table(name = "user_address")
    public class Address {
        @Id
        private String addrId;
        private String flatNo;
        private String floor;
        private String building;
        private String apts;
        private String area;
        private String city;
        private String state;
        private String pin;

        public Address(Address address) {
            this.setAddrId(address.getAddrId());
            this.setFlatNo(address.getFlatNo());
            this.setFloor(address.getFloor());
            this.setBuilding(address.getBuilding());
            this.setApts(address.getApts());
            this.setArea(address.getArea());
            this.setCity(address.getCity());
            this.setState(address.getState());
            this.setPin(address.getPin());
        }
    }
