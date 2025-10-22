文件名称
```text
LC_${question.frontendQuestionId}_$!velocityTool.replaceAll(${question.title},"[\s\[\]\(\)]","")
```
代码模版
```text
${question.content}
package come.czy.problems;

class LC_${question.frontendQuestionId}_$!velocityTool.replaceAll(${question.title},"[\s\[\]\(\)]","") {
    //$!velocityTool.date()
    //${question.title}
    //编号：[$!{question.frontendQuestionId}]
    
    public static void main(String[] args) {
        Solution solution = new LC_${question.frontendQuestionId}_$!velocityTool.replaceAll(${question.title},"[\s\[\]\(\)]","")().new Solution();
        // TO TEST
    }
    ${question.code}
}
```