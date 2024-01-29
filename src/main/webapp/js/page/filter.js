function initFilter(){
    const filter = document.querySelector("input[data-filter]");
    if(filter){
        const submit = filter.nextElementSibling;
        filter.addEventListener('keydown',(e)=>{
            if(e.key==="Enter"){
                filterWith(filter.value);
            }
        })
        if(submit){
            submit.addEventListener('click',()=>{
                filterWith(filter.value);
            })
        }
    }
}

function filterWith(value){
    if(value.trim()){
        location.href="/review"+value;
    }
}

window.addEventListener('load',()=>{
    initFilter();
    moderationFilter();
})

function moderationFilter(){
    const selector = document.querySelector("select[moderationFilter]");
    var url = new URL(location.href);
    if(selector){
        selector.addEventListener('change',()=>{
            const val = selector.value;
            if(val){
                if(parseInt(val)===0){
                    url.searchParams.delete("moderationFilter");
                }
                if(parseInt(val)>=1){
                    url.searchParams.set("moderationFilter",val);
                }
            }
            url.search = url.search.replaceAll("%2C",",");
            location.href=url.href;
        })
        const val = url.searchParams.get("moderationFilter");
        if(val && parseInt(val)>=0){
            selector.value=val;
        }
    }
}