export function includeValidation() {

    const documentIDs = {
        GRAPH_CLICK_SUBMIT_FORM: "form",
        TABLE_OF_CLICKS: "my_table",
        SUBMIT_BUTTON: "submit_button",
        RESET_BUTTON: "reset_button",
        INPUT_X: "input_x",
        WARNING_TEXT: "input_x_warning",
    }
    const xInputElement = document.getElementById(documentIDs.INPUT_X);
    const submitButtonElement = document.getElementById(
        documentIDs.SUBMIT_BUTTON
    );
    const warningElement = document.getElementById(documentIDs.WARNING_TEXT);

    function hideWarning() {
        warningElement.innerHTML = "";
        warningElement.hidden = true;
    }

    function enableSubmitButton() {
        submitButtonElement.disabled = false;
    }

    function showLimitsWarning() {
        warningElement.innerHTML = "X must be a number in (-3; 3)";
        warningElement.hidden = false;
    }

    function showLongNumberWarning() {
        warningElement.innerHTML = "Your number is too long. We do not support long numbers :)";
        warningElement.hidden = false;
    }

    function disableSubmitButton() {
        submitButtonElement.disabled = true;
    }

    function validateX(_event) {
        const numberPattern = /^[+-]?(\d*[.,])?\d+$/;

        const x = parseFloat(xInputElement.value);
        if (xInputElement.value.length > 14) {
            showLongNumberWarning()
            disableSubmitButton()
        } else if (
            Number.isNaN(x) ||
            !numberPattern.test(xInputElement.value) ||
            x <= -3 ||
            x >= 3
        ) {
            showLimitsWarning();
            disableSubmitButton();
        } else {
            hideWarning();
            enableSubmitButton();
        }
    }

    xInputElement.addEventListener("input", validateX);
    xInputElement.dispatchEvent(new Event("input"));
}


