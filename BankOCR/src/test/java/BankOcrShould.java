import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BankOcrShould {


    @Test
    public void return_all_zeros_when_given_nine_zeros(){
        BankOcr bankOcr = new BankOcr();
        String number = bankOcr.parseString(new String[]{" _  _  _  _  _  _  _  _  _ ",
                                                         "| || || || || || || || || |",
                                                         "|_||_||_||_||_||_||_||_||_|"});
        assertThat(number).isEqualTo("000000000");
    }

    @Test
    public void return_all_ones_when_given_nine_ones(){
        BankOcr bankOcr = new BankOcr();
        String number = bankOcr.parseString(new String[]{"                           ",
                                                         "  |  |  |  |  |  |  |  |  |",
                                                         "  |  |  |  |  |  |  |  |  |"});
        assertThat(number).isEqualTo("111111111");
    }

    @Test
    public void return_correct_value_for_all_numbers_from_one_to_nine(){
        BankOcr bankOcr = new BankOcr();
        String number = bankOcr.parseString(new String[]{"    _  _     _  _  _  _  _ ",
                                                         "  | _| _||_||_ |_   ||_||_|",
                                                         "  ||_  _|  | _||_|  ||_| _|"});
        assertThat(number).isEqualTo("123456789");
    }

    @Test
    public void return_interrogation_mark_in_String_when_number_is_not_adequate(){
        BankOcr bankOcr = new BankOcr();
        String number = bankOcr.parseString(new String[]{"    _  _     _  _  _  _  _ ",
                                                         "  | _| _||_||_ |_   ||_||_|",
                                                         "   |_  _|  |  ||_|  ||_| _|"});
        assertThat(number).isEqualTo("?234?6789");
    }

    @Test
    public void return_true_when_given_correct_account_number(){
        BankOcr bankOcr = new BankOcr();
        assertThat(bankOcr.isValidChecksum("345882865")).isTrue();
        assertThat(bankOcr.isValidChecksum("123456789")).isTrue();
    }

    @Test
    public void return_false_when_given_incorrect_account_number(){
        BankOcr bankOcr = new BankOcr();
        assertThat(bankOcr.isValidChecksum("345882860")).isFalse();
    }

    @Test
    public void return_correct_string_result_for_correct_account_number(){
        BankOcr bankOcr = new BankOcr();
        String number = bankOcr.getInputResult(new String[]{"    _  _     _  _  _  _  _ ",
                                                            "  | _| _||_||_ |_   ||_||_|",
                                                            "  ||_  _|  | _||_|  ||_| _|"});
        assertThat(number).isEqualTo("123456789");
    }

    @Test
    public void return_correct_string_result_for_incorrect_account_number(){
        BankOcr bankOcr = new BankOcr();
        String number = bankOcr.getInputResult(new String[]{" _  _  _     _  _  _  _  _ ",
                                                            "  | _| _||_||_ |_   ||_||_|",
                                                            "  ||_  _|  | _||_|  ||_| _|"});
        assertThat(number).isEqualTo("723456789 ERR");
    }

    @Test
    public void return_correct_string_result_for_in_invalid_account_number(){
        BankOcr bankOcr = new BankOcr();
        String number = bankOcr.getInputResult(new String[]{" |  _  _     _  _  _  _  _ ",
                                                            "  | _| _||_||_ |_   ||_||_|",
                                                            "  ||_  _|  | _||_|  ||_| _|"});
        assertThat(number).isEqualTo("?23456789 ILL");
    }

}
