SELECT *
FROM `PowerLogger`.`RealTime` rt
         INNER JOIN `PowerLogger`.`CurrentCombinedPhases` c
                    ON rt.realtimeId = c.Realtime_id;

CREATE VIEW `PowerLogger`.`CurrentCombinedPhases` as
SELECT Realtime_id,
       sum(IF(Phase = 1, I, 0))     as "Ia",
       sum(IF(Phase = 2, I, 0))     as "Ib",
       sum(IF(Phase = 3, I, 0))     as "Ic",
       sum(IF(Phase = 0, I, 0))     as "In",
       sum(IF(Phase = 1, Ubl_I, 0)) as "Ubl_Ia",
       sum(IF(Phase = 2, Ubl_I, 0)) as "Ubl_Ib",
       sum(IF(Phase = 3, Ubl_I, 0)) as "Ubl_Ic",
       sum(IF(Phase = 0, Ubl_I, 0)) as "Ubl_In"
FROM `PowerLogger`.`Current`
group by Realtime_id;

CREATE VIEW `PowerLogger`.`VoltageCombinedPhases` as
SELECT Realtime_id,
       sum(IF(Phase = 1, U_ln, 0))     as Ua,
       sum(IF(Phase = 2, U_ln, 0))     as Ub,
       sum(IF(Phase = 3, U_ln, 0))     as Uc,
       sum(IF(Phase = 1, U_ll, 0))     as Uab,
       sum(IF(Phase = 2, U_ll, 0))     as Ubc,
       sum(IF(Phase = 3, U_ll, 0))     as Uca,
       sum(IF(Phase = 1, Ubl_U_ln, 0)) as Ubl_Ua,
       sum(IF(Phase = 2, Ubl_U_ln, 0)) as Ubl_Ub,
       sum(IF(Phase = 3, Ubl_U_ln, 0)) as Ubl_Uc,
       sum(IF(Phase = 1, Ubl_U_ll, 0)) as Ubl_Uab,
       sum(IF(Phase = 2, Ubl_U_ll, 0)) as Ubl_Ubc,
       sum(IF(Phase = 3, Ubl_U_ll, 0)) as Ubl_Uca
FROM `PowerLogger`.`Voltage`
group by Realtime_id;

CREATE VIEW `PowerLogger`.`PFCombinedPhases` as
SELECT Realtime_id,
       sum(IF(Phase = 1, PF, 0)) as PFa,
       sum(IF(Phase = 2, PF, 0)) as PFb,
       sum(IF(Phase = 3, PF, 0)) as PFc
FROM `PowerLogger`.`PowerFactor`
group by Realtime_id;

CREATE VIEW `PowerLogger`.`PowerCombinedPhases` as
SELECT Realtime_id,
       sum(IF(Phase = 1, "active", 0))   as P_Pa,
       sum(IF(Phase = 2, "active", 0))   as P_Pb,
       sum(IF(Phase = 3, "active", 0))   as P_Pc,
       sum(IF(Phase = 1, "apparent", 0)) as P_Sa,
       sum(IF(Phase = 2, "apparent", 0)) as P_Sb,
       sum(IF(Phase = 3, "apparent", 0)) as P_Sc,
       sum(IF(Phase = 1, "reactive", 0)) as P_Qa,
       sum(IF(Phase = 2, "reactive", 0)) as P_Qb,
       sum(IF(Phase = 3, "reactive", 0)) as P_Qc
FROM `PowerLogger`.`Power`
group by Realtime_id;

CREATE VIEW `PowerLogger`.`EnergyCombinedPhases` as
SELECT device,
        timestamp ,
        sum (IF(Import=1,"active",0)) as E_P_Import,
        sum (IF(Import=0,"active",0)) as E_P_Export,
        sum (IF(Import=1,"apparent",0)) as E_S_Import,
        sum (IF(Import=0,"apparent",0)) as E_S_Export,
        sum (IF(Import=1,"reactive",0)) as E_Q_Import,
        sum (IF(Import=0,"reactive",0)) as E_Q_Export
        FROM `PowerLogger`.`Energy`
        group by timestamp,device;

SELECT *
FROM `PowerLogger`.`CurrentCombinedPhases`;