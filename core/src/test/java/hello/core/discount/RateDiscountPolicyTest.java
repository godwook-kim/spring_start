package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;

import org.junit.jupiter.api.DisplayName;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPolicyTest {
    RateDiscountPolicy rateDiscountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP는 10프로 할인이 적용되어야 한다.")
    void vip_is() {
        // given
        Member member = new Member(1L, "memberVip", Grade.VIP);
        // when
        int discount = rateDiscountPolicy.discount(member,20000);
        // then
        Assertions.assertThat(discount).isEqualTo(2000);

    }

    @Test
    @DisplayName("VIP가 아닌 경우 할인이 적용되지 않아야 한다.")
    void vip_is_not(){
        // given
        Member member = new Member(1L, "memberVip", Grade.BASIC);
        // when
        int discount = rateDiscountPolicy.discount(member,20000);
        // then
        Assertions.assertThat(discount).isEqualTo(0);
    }


}