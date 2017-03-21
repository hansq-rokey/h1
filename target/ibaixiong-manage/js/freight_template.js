function add(){
			$("#spanTitle").html("新增运费模板");
			var $this = $("input[name='status']:checked");
			/* if(value==10){
				$(".p_row,.s_row,.m_row,.n_row").hide();
			} */
			show($this);
			$('.addpop').show();
		}
    	//编辑模板
    	function update(id){
    		$("#id").val(id);
    		$.ajax({
    			url:"/mall/template/update.html?id="+id,
    			type:"POST",
    			async:false,
    			dataType:"JSON",
    			success:function(data){
    				var template = data.result.template;
    				var value = data.result.value;
    				var values = data.result.values;
    				$('.addpop').show();
    				$("#name").val(template.name);
    				var sendType = template.sendType;
    				$("#checkbox1,#checkbox2,#checkbox3,#checkbox4").attr("disabled","true"); 
    				/* //全国包邮
    				if(sendType==10){
    					$(".p_row,.s_row,.m_row,.n_row").hide();
    					$("input[name='status']").removeAttr("checked");
        				$("#checkbox1").attr("checked","checked");
    				} */
    				//自定义
    				if(sendType==20){
    					$("input[name='status']").removeAttr("checked");
    					$("#checkbox2").attr("checked","checked");
    				}
    				//按件数计算
    				if(value.valuationType==1){
    					public_template("n_row","m_row",values,value)
    				}
    				//按重量计算
    				if(value.valuationType==2){
    					public_template("m_row","n_row",values,value)
    				}
    				showLocation();
    			}
    		});
    	}
    	
    	var public_template = function (thisClass,sibClass,values,value){
    		var c = "."+thisClass;
    		$("input[name='type']").removeAttr("checked");
			$("#checkbox4").attr("checked","checked"); 
			$("."+sibClass).hide();
			$(c).find("#numInnerValue").val(value.numInner);
			$(c).find("#priceInnerValue").val(value.priceInner);
			$(c).find("#numOutValue").val(value.numOut);
			$(c).find("#priceOutValue").val(value.priceOut);
			//console.log(value.numInner+"-"+value.priceInner+"-"+value.numOut+"-"+value.priceOut);
			$("#m_name").val(value.numInner+"-"+value.priceInner+"-"+value.numOut+"-"+value.priceOut);
			if(values.length > 0){
				$.each(values,function(i,item){
					var v = this.valueAreas;
					var provinceIds = "";
					var box = "";
					var va = "";
					for(var i=0;i<v.length;i++){
						provinceIds += v[i].provinceId+"=";
						box +='<span class="sele_box" data-id="'+v[i].provinceId+'" style="display: inline-block;">'+v[i].provinceName+'</span> ';
					   	va +='<input type="hidden" name="province_id" value="'+v[i].provinceId+'">'
					}
					var dName = provinceIds+"-"+this.numInner+"-"+this.priceInner+"-"+this.numOut+"-"+this.priceOut;
					var html = '<tr class="t_link" data-id="'+this.id+'"><td><p class="s_text">'+box+'</p><p class="s_value">'+va+'</p><div class="addressSelect clearfix" style="width: 140px;"><div class="sele prov"><em class="txt">省份</em><ul class="loc loc_province"></ul></div></div></td><td><input type="text" class="urlvalue" name="numInner" id="numInner" value="'+this.numInner+'"/></td><td><input type="text" class="urlvalue" name="priceInner" id="priceInner" value="'+this.priceInner+'"/></td><td><input type="text" class="urlvalue" name="numOut" id="numOut" value="'+this.numOut+'"/></td><td><input type="text" class="urlvalue" name="priceOut" id="priceOut" value="'+this.priceOut+'"/></td><td><a class="deletelink" href="javascript:;" onclick="appendData(this)">确认</a> <input type="hidden" name="d_name" class="d_name" value="'+dName+'"/><a class="deletelink" href="javascript:;" onclick="deleteHtml(this)">删除</a></td></tr>';
					$("."+thisClass).children("table").append(html);
				});
			}
    	} 
    	//删除模板
    	function deleteTemplate(id){
    		layer.confirm('是否删除本行？', {
  			  btn: ['确定','否'] //按钮
  			},function(){
  				$.ajax({
  	    			url:"/mall/template/deleteTemplate.html?id="+id,
  	    			type:"POST",
  	    			async:false,
  	    			dataType:"JSON",
  	    			success:function(data){
  	    				if(data.success){
  	    					window.location.href="/mall/template/list.html";
  	    		  			layer.closeAll('dialog');
  	    					//setTimeout(function(){window.location.href="/mall/template/list.html";},1000);
  	    				}
  	    			}
  	    		});
  			//layer.msg('删除成功', {icon: 1},{layer.closeAll('dialog')});
  		})
    	}
    	//单选框选择事件
    	function show(obj){
    		var value = $(obj).val();
    		var id = $("#id").val();
   			/* //全国包邮
       		if(value==10){
       			$("input[name='status']").removeAttr("checked");
       			$(obj).attr("checked","checked"); 
       			$(".p_row,.s_row,.m_row,.n_row").hide();
       		} */
       		//自定义
       		if(value==20){
       			$("input[name='status']").removeAttr("checked");
       			$(obj).attr("checked","checked"); 
       			$(".p_row,.s_row").show();
       			var data = $("input[name='type']:checked").val();
       			if(data==1){
       				$(".n_row").show();
       				$(".m_row").hide();
       			}
       			if(data==2){
       				$(".m_row").show();
       				$(".n_row").hide();
       			}
       		}
       		//按件数
       		if(value==1){
       			public_check("m_row","n_row",obj);
       		}
       		//按重量
       		if(value==2){
       			public_check("n_row","m_row",obj);
       		}
    	}
    	var public_check = function(thisClass,sibClass,obj){
    		var c = "."+thisClass;
    		$("input[name='type']").removeAttr("checked");
   			$(obj).attr("checked","checked"); 
   			$("."+thisClass).hide();
   			$(".n_row").children(".d_name").val("");
   			$(".n_row").children(".t_link").remove();
   			$(".n_row").children("#numInnerValue").val("");
   			$(".n_row").children("#priceInnerValue").val("");
   			$(".n_row").children("#numOutValue").val("");
   			$(".n_row").children("#priceOutValue").val("");
   			$("#location_id").val("");
   			$("#m_name").val("");
   			$("."+sibClass).show();
    	}
    	//新增指定区域运费
    	function addArea(obj){
    		var link_length=$('.t_link').length+1;
    		var arr=[];
    		arr.push('<tr class="t_link">');
    		arr.push('<td><p class="s_text"></p><p class="s_value"></p><div class="addressSelect clearfix" style="width: 140px;"><div class="sele prov"><em class="txt">省份</em><ul class="loc loc_province province'+link_length+'"></ul></div></div></td>');
    		arr.push('<td><input type="text" class="urlvalue" name="numInner" id="numInner" value=""/></td>');
    		arr.push('<td><input type="text" class="urlvalue" name="priceInner" id="priceInner" value=""/></td>');
    		arr.push('<td><input type="text" class="urlvalue" name="numOut" id="numOut" value=""/></td>');
    		arr.push('<td><input type="text" class="urlvalue" name="priceOut" id="priceOut" value=""/></td>');
    		arr.push('<td><a class="deletelink" href="javascript:;" onclick="appendData(this)">确认</a> <input type="hidden" name="d_name" class="d_name" value=""/><a class="deletelink" href="javascript:;" onclick="deleteHtml(this)">删除</a></td>');
    		arr.push('</tr>');
//    		var html = '';
    		var inpLength = $(obj).parent().siblings("table").find("tr").length;
    		var inpValue = $(obj).parent().siblings("table").find("tr").eq(inpLength-1).children().children(".d_name").val();
    		if(inpValue==""){
    			layer.msg("请先点击确认，再新增！");
    			return;
    		}
    		$(obj).parent().parent().children("table").append(arr.join(''));
    		showLocation_addCity($('.province'+link_length+''));
    	}
    	//默认运费鼠标移开触发函数
    	function appendHtml(obj){
    		var numInner = $(obj).siblings("#numInnerValue").val();
    		var priceInner = $(obj).siblings("#priceInnerValue").val();
    		var numOut = $(obj).siblings("#numOutValue").val();
    		var priceOut = $(obj).siblings("#priceOutValue").val();
    		if(numInner!=null && priceInner!=null && numOut!=null && priceOut!=null 
    				&& numInner!="" && priceInner!="" && numOut!="" && priceOut!=""){
    			$("#m_name").val(numInner+"-"+priceInner+"-"+numOut+"-"+priceOut);
    		}
    	}
    	//点击确认按钮
    	function appendData(obj){
    		var numInner = $(obj).parent().siblings().children("#numInner").val();
    		var priceInner = $(obj).parent().siblings().children("#priceInner").val();
    		var numOut = $(obj).parent().siblings().children("#numOut").val();
    		var proValue = $(obj).parent().siblings().children(".clearfix").children(".prov").children(".txt").text();
    		var priceOut = $(obj).parent().siblings().children("#priceOut").val();
    		var len = $(obj).parent().siblings().children(".s_text").children(".sele_box").length;
    		var provinceIds="";
    		for(var i=0;i<len;i++){
    			provinceIds += $(obj).parent().siblings().children(".s_text").children(".sele_box").eq(i).attr("data-id")+"=";
    		}
    		if(provinceIds!=null && provinceIds!="" && proValue!=null && proValue!="" && numInner!=null && priceInner!=null 
    			&& numOut!=null && priceOut!=null && numInner!="" && priceInner!="" && numOut!="" && priceOut!=""){
    			$(obj).siblings(".d_name").val(provinceIds+"-"+numInner+"-"+priceInner+"-"+numOut+"-"+priceOut);
    		}else{
    			layer.msg("请检查是否有空值！");
    			return;
    		}
    	}
    	//点击保存
    	function submitData(){
    		var templateName = $("#name").val();
    		var sendType = $("input[name='status']:checked").val();
    		var valuationType = $("input[name='type']:checked").val();
    		var d_name = "";
    		var d_value = document.getElementsByName("d_name");
    		for(var i=0;i<d_value.length;i++){
    			if(d_value[i].value==null || d_value[i].value==""){
    				layer.msg("请先点击确认按钮再保存数据！");
    				return;
    			}
    			d_name+=d_value[i].value+",";
    		}
    		var m_name = $("#m_name").val();
    		if(templateName=="" || templateName==null){
    			layer.msg("模板名称不能为空");
    			return;
    		}
    		if(sendType==20 && valuationType!=null && valuationType!=""){
    			if(m_name=="" || m_name==""){
    				layer.msg("默认运费选项不能为空");
    				return;
    			}
    		}
    		var id = $("#id").val();//判断是新增还是编辑
    		$.ajax({
    			url:"/mall/template/add.html",
    			type:"POST",
    			data:{"name":templateName,"sendType":sendType,"valuationType":valuationType,"m_name":m_name,"d_name":d_name,"id":id},
    			dataType:"JSON",
    			success:function(data){
    				if(data.success){
    					setTimeout(function(){window.location.href="/mall/template/list.html";},1000);
    				}else{
    					layer.msg(data.message);
    				}
    			}
    		});
    	}
    	//删除某一行运费
    	function deleteHtml(obj){
    		layer.confirm('是否删除本行？', {
    			  btn: ['确定','否'] //按钮
    		},function(){
    			$(obj).parent().parent().remove(),
    			layer.closeAll('dialog');
    			//layer.msg('删除成功', {icon: 1},{layer.closeAll('dialog')});
    		})
    		//$(obj).parent().parent().remove();
    	}
    	function closeMethod(){
    		window.location.href="/mall/template/list.html";
    	}
    	$(function(){//删除城市
    	  	$(document).on("click",".s_text>.sele_box",function(){
    	  		var that=$(this);
    	  		var id=$(this).attr("data-id");//当前模板
    	  		var listid=$(this).parents(".t_link").attr("data-id");//当前这条记录id
    	  		$.ajax({
    				url:"/mall/template/deleteArea.html",
    				type:"POST",
    				data:{"templateValueId":listid,"cityId":id},
    				dataType:"JSON",
    				success:function(data){
    					if(data.success){
    						that.remove();
    					}else{
    						layer.msg("删除失败");
    					}
    				}
    	    	});    	  		
    	  	});
    	});